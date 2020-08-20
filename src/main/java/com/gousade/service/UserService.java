package com.gousade.service;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gousade.config.ShiroRealm;
import com.gousade.mapper.UserMapper;
import com.gousade.mapper.UserRoleMapper;
import com.gousade.pojo.User;
import com.gousade.pojo.UserRole;
import com.gousade.pojo.util.AttachmentGeneral;
import com.gousade.utils.DataTablesPageUtil;
import com.gousade.utils.SaltUtil;


@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private ShiroRealm shiroRealm;
	
	public User selectByPrimaryKey(String id) {
		User user = userMapper.selectByPrimaryKey(id);
		List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(user.getId());
		user.setRoleIds(String.join(",", roleIdList));
		return user;
	}
	
	public User SelectUserByLoginName(String userId){
		return userMapper.SelectUserByLoginName(userId);
	}

	public Map<String, Object> regist(Map<String, Object> map) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			String firstpsd=(String) map.get("password");
			String uid = SaltUtil.getUUId();
			String uidsalt = DigestUtils.md5Hex(uid);
			String psd = firstpsd.concat(uidsalt);
			String password = DigestUtils.md5Hex(psd);
			map.put("salt", uid);
			map.put("password", password);
			int i=userMapper.regist(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "注册成功,跳转到登录页面");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "注册失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retMap;
	}
	
	public Map<String, Object> ShiroRegist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			String firstpsd=(String) map.get("password");
			String salt=SaltUtil.getUUId();
			String password =SaltUtil.toHex(firstpsd, salt);
			map.put("salt", salt);
			map.put("password", password);
			int i=userMapper.regist(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "注册成功,跳转到登录页面。");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "注册失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retMap;
	}
	
	public DataTablesPageUtil<User> selectUserList(DataTablesPageUtil<User> dataTables) {
		List<User> list = userMapper.selectUserList(dataTables.getSearchMap());
		/*for(User user : list){
			List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(user.getUserId());
			user.setRoleIds(String.join(",", roleIdList));
		}*/
		dataTables.setData(list);
		return dataTables;
	}
	
	public long queryuserlistcnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.queryuserlistcnt(map);
	}
	
	public int updateLoginTime(String id) {
		int i=userMapper.updateLoginTime(id);
		return i;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateUserById(User entity) {
		userRoleMapper.deleteByUserId(entity.getId());
		insertUserRole(entity);
		if(!StringUtils.isBlank(entity.getPassword())){
			entity.setSalt(SaltUtil.generateUUId());
			entity.setPassword(SaltUtil.toHex(entity.getPassword(), entity.getSalt()));
		}
		//修改密码后要清除shiro缓存中的信息，否则缓存依旧匹配旧密码，新密码无法登录。
		/*Cache<Object,AuthenticationInfo> authentication=shiroRealm.getAuthenticationCache();
		if (authentication!=null){
			authentication.remove(entity.getId());
		}*/
		return userMapper.updateUserById(entity)>0;
	}

	private void insertUserRole(User entity) {
		if (!StringUtils.isBlank(entity.getRoleIds())) {
			String[] roles = entity.getRoleIds().split(",");
			UserRole userRole = new UserRole();
			for (String roleId : roles) {
				userRole.setId(SaltUtil.getUUId());
				userRole.setUserId(entity.getId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
		}
	}

	public boolean insert(User user) {
		user.setId(SaltUtil.generateUUId());
		user.setSalt(SaltUtil.getUUId());
		user.setPassword(SaltUtil.toHex(user.getPassword(), user.getSalt()));
		return userMapper.insert(user)>0;
	}

	public boolean deleteUserByid(Map<String, Object> map) {
		return userMapper.deleteUserByid(map)>0;
	}

	public boolean uploadUserAvatar(AttachmentGeneral attachmentGeneral) {
		return userMapper.uploadUserAvatar(attachmentGeneral)>0;
	}

	public boolean updateOwnPasswordById(User user) {
		return userMapper.updateOwnPasswordById(user)>0;
	}
}
