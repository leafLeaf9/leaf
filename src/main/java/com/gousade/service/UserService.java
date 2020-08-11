package com.gousade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gousade.config.ShiroRealm;
import com.gousade.mapper.UserMapper;
import com.gousade.mapper.UserRoleMapper;
import com.gousade.pojo.Menu;
import com.gousade.pojo.User;
import com.gousade.pojo.UserRole;
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

	public List<Menu> listAdminMenuByRole(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Menu> menuList;
	     List<Menu> newMenuList = new ArrayList<>();
	     try {
	         //1、根据角色获得所有的菜单（包括一级和二级）
	         menuList = userMapper.listMenuByRoleId(map);
	         for (int i = 0; i < menuList.size(); i++) {
	             Menu menu = menuList.get(i);
	             List<Menu> childMenuList = new ArrayList<>();
	             //2、拼装二级菜单
	             if (menu.getPid() == 0) {//检测pid是否为0，为0代表是父菜单，如果是父菜单则检测刚开始获得的菜单里有哪些是它的子菜单，把子菜单
	            	                      //添加进这个menu的ChildMenu中，再把menu放到最后返回的数据中。如果刚开始获得的菜单里只有子菜单那么返回值将为空
	                 for (int j = 0; j < menuList.size(); j++) {
	                     if (Objects.equals(menu.getId(), menuList.get(j).getPid())) {
	                         childMenuList.add(menuList.get(j));
	                     }
	                 }
	                 menu.setChildMenu(childMenuList);
	                 newMenuList.add(menu);
	             }
	         }
	     } catch (Exception e) {
	         System.out.println("获取菜单出错");
	     }
	     return newMenuList;
	}

	public Map<String, Object> getroleidByuid(Map<String, Object> map) {
		return userMapper.getroleidByuid(map);
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
		Cache<Object,AuthenticationInfo> authentication=shiroRealm.getAuthenticationCache();
		if (authentication!=null){
			authentication.remove(entity.getId());
		}
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
}
