package com.gousade.service;

import com.gousade.mapper.UserMapper;
import com.gousade.mapper.UserRoleMapper;
import com.gousade.pojo.Menu;
import com.gousade.pojo.User;
import com.gousade.pojo.UserRole;
import com.gousade.utils.DataTablesPageUtil;
import com.gousade.utils.SaltUtil;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public User toCheck(String userId,String password){
		// MD5加密后与数据库中数据比较
		password = DigestUtils.md5Hex(password);
	//	System.out.println("加密之后的密码是"+password);
		return userMapper.toCheckUser(userId,password);
	}
	
	public User SelectUserByLoginName(String userId){
		return userMapper.SelectUserByLoginName(userId);
	}

	public Map<String, Object> regist(Map<String, Object> map) {
		// TODO Auto-generated method stub
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
	
	public Map<String, Object> insertuser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			String password = DigestUtils.md5Hex((String)map.get("password"));
			map.put("password", password);
			int i=userMapper.insertuser(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "新增用户成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "新增用户失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retMap;
	}
	
	public Map<String, Object> updateuser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			map.put("password", DigestUtils.md5Hex((String)map.get("password")));
			int i=userMapper.updateuser(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "修改用户信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "修改用户信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
	
	public Map<String, Object> delusers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=userMapper.delusers(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "删除用户信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "删除用户信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
	
	public Map<String, Object> resetpsd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		
			Map<String, Object> psdmap = new HashMap<String, Object>();
			psdmap=userMapper.getpsdByuid(map);
			String password=(String) psdmap.get("password");
			String currPsd = DigestUtils.md5Hex((String)map.get("currPsd"));
			if(currPsd.equals(password)) {
				try {
					map.put("password", DigestUtils.md5Hex((String)map.get("password")));
				int i=userMapper.resetpsd(map);
				
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "重置密码成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "重置密码失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
			}else {
				retMap.put("result", "原密码错误");
				return retMap;
			}
			
	}
	

	public Map<String, Object> selectphonenum(Map<String, Object> map) {
		return userMapper.selectphonenum(map)	;	
	}
	
	public Map<String, Object> cleanpsd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		int  newcode =SmsDemo.getNewcode(); 
		System.out.println("从Sms获得的验证码是："+newcode);
		int code=Integer.parseInt((String) map.get("code")) ;
		if(code==newcode)	{
			String initpsd="123456";
			map.put("password", DigestUtils.md5Hex(initpsd));
			int i=userMapper.cleanpsd(map);
			if(i>=1) {
				//retMap.put("success", true);
				retMap.put("result", "恢复初始密码成功!\n初始密码为123456，请妥善保存。");
			}else {
			//	retMap.put("success", false);
				retMap.put("result", "恢复初始密码失败");
			}
			return retMap;
		}else {
			retMap.put("result", "验证码输入错误");
			return retMap;
		}	
		
	}
	
	public List<Map<String, Object>> queryuserlist(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return userMapper.queryuserlist(paraMap);
	}
	
	public DataTablesPageUtil<User> selectUserList(DataTablesPageUtil<User> dataTables) {
		List<User> list = userMapper.selectUserList(dataTables.getSearchMap());
		for(User user : list){
			List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(user.getUserId());
			user.setRoleIds(String.join(",", roleIdList));
		}
		dataTables.setData(list);
		return dataTables;
	}
	
	public String setrule(Map<String, String> map) {
		int i= userMapper.setrule(map);
		if(i>=0) {
			return "设置成功";
		}
		return "设置失败";
		
	}
	
	public long queryuserlistcnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.queryuserlistcnt(map);
	}

	public String declare(Map<String, String> map) {
		// TODO Auto-generated method stub
		int i= userMapper.declare(map);
		if(i>=1) {
			return "申报成功";
		}
		return "申报失败";
	}

	public List<Map<String,Object>> queryprojlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		/*int i= userMapper.declare(map);
		if(i>=0) {
			return "申报成功";
		}
		return "申报失败";*/
		return userMapper.queryprojlist(map);
	}

	public Map<String, Object> firstpassbyid(Map<String, Object> map) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=userMapper.firstpassbyid(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					String received= (String) userMapper.selectidbyproj(map).get("user_id");
					//System.out.println(received);
					retMap.put("result", "修改状态成功");
					messageService.toMessage(received, "您好，您的项目已经通过初审");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "修改状态失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
	public Map<String, Object> dopassbyid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=userMapper.dopassbyid(map);
			
				if(i>=1) {
					if(map.get("state").equals("4")) {
						String received= (String) userMapper.selectidbyproj(map).get("user_id");
					messageService.toMessage(received, "您好，您的项目已经成功立项");
					}
					//retMap.put("success", true);
					retMap.put("result", "修改状态成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "修改状态失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}

	public Map<String, Object> assignexperts(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=userMapper.assignexperts(map);
			
			
				if(i>=1) {
					userMapper.secondpassbyid(map);
					String received= (String) userMapper.selectidbyproj(map).get("user_id");	
					messageService.toMessage(received, "您好，您的项目已经分配完专家");
					//retMap.put("success", true);
					retMap.put("result", "专家分配成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "专家分配失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retMap;
	}

	public Map<String, Object> savegrading(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=userMapper.savegrading(map);
		
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "打分成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "打分失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, Object>> list=	userMapper.querygradestate(map);
		System.out.println(list);
		if(list.isEmpty()) {
			userMapper.thirdpassbyid(map);
			String received= (String) userMapper.selectidbyproj(map).get("user_id");	
			messageService.toMessage(received, "您好，所有专家已对您的项目完成打分");
			System.out.println("所有专家已打分，变更状态为立项评审完成");
		}
		return retMap;
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
		// TODO Auto-generated method stub
		return userMapper.getroleidByuid(map);
	}
	
	public int updateLoginTime(String userId) {
		int i=userMapper.updateLoginTime(userId);
		return i;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateUserById(User entity) {
		userRoleMapper.deleteByUserId(entity.getUserId());
		insertUserRole(entity);
		return userMapper.updateUserById(entity)>0;
	}

	private void insertUserRole(User entity) {
		if (!StringUtils.isBlank(entity.getRoleIds())) {
			String[] roles = entity.getRoleIds().split(",");
			UserRole userRole = new UserRole();
			for (String roleId : roles) {
				userRole.setId(SaltUtil.getUUId());
				userRole.setUserId(entity.getUserId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
		}
	}

	public boolean insert(User user) {
		user.setSalt(SaltUtil.getUUId());
		user.setPassword(SaltUtil.toHex(user.getPassword(), user.getSalt()));
		return userMapper.insert(user)>0;
	}
}
