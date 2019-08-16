package cn.edu.zstu.manage.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zstu.manage.mapper.RoleMapper;
import cn.edu.zstu.manage.mapper.UserMapper;
import cn.edu.zstu.manage.pojo.Menu;
import cn.edu.zstu.manage.pojo.User;

@Service
public class RoleService {

	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	public List<Map<String, Object>> queryrolelist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleMapper.queryrolelist(map);
	}
	
	public Map<String, Object> insertrole(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			
			int i=roleMapper.insertrole(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "新增角色成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "新增角色失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retMap;
	}
	
	public Map<String, Object> updaterole(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=roleMapper.updaterole(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "修改角色信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "修改角色信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
		
	}

	public Map<String, Object> delroles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=roleMapper.delroles(map);
			
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

	public List<Map<String, Object>> querymenulist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleMapper.querymenulist(map);
	}

	public Map<String, Object> updatemenu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=roleMapper.updatemenu(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "修改菜单信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "修改菜单信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
		
	}
	
	public Map<String, Object> delmenus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=roleMapper.delmenus(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "删除菜单信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "删除菜单信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
}
