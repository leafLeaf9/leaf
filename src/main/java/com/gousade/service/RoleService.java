package com.gousade.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.RoleResourceMapper;
import com.gousade.pojo.Role;
import com.gousade.pojo.RoleResource;
import com.gousade.utils.SaltUtil;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
    private RoleResourceMapper roleResourceMapper;
	
	public List<Role> selectRoleList(Map<String,Object> map) {
        List<Role> list = roleMapper.selectRoleList(map);
        return list;
    }
	
	public Map<String, Object> insertrole(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		role.setId(SaltUtil.getUUId());
		int i=roleMapper.insert(role);
		if(i>=1) {
			map.put("status", true);
			map.put("msg", "新增角色成功");
		}else {
			map.put("status", false);
			map.put("msg", "新增角色失败");
		}
		return map;
	}
	
	public Map<String, Object> updaterole(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i=roleMapper.updateByPrimaryKey(role);
		if(i>=1) {
			map.put("status", true);
			map.put("msg", "编辑角色成功");
		}else {
			map.put("status", false);
			map.put("msg", "编辑角色失败");
		}
		return map;
	}
	
	public Map<String, Object> deleteBatchIds(List<? extends String> idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i=roleMapper.deleteBatchIds(idList);
		if(i>=1) {
			map.put("status", true);
			map.put("msg", "删除角色成功");
		}else {
			map.put("status", false);
			map.put("msg", "删除角色失败");
		}
		return map;
	}
	
	public Map<String, Object> selectResourceIdListByRoleId(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> resources = roleMapper.selectResourceIdListByRoleId(id);
		resources.removeAll(Collections.singleton(null));//由于角色无资源时返回的是[null]，把它变为[]
		map.put("status", true);
		map.put("resultData", resources);
		return map;
    }
	
	public Map<String, Object> saveRoleAuthorize(String roleId, String[] resourceIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleResourceMapper.deleteByRoleId(roleId);
		List<RoleResource> list=new ArrayList<RoleResource>();
		if(resourceIds!=null){
			List<String> resourceIdlist=Arrays.asList(resourceIds);
			for (String resourceId : resourceIdlist) {
				RoleResource roleResource = new RoleResource();
                roleResource.setId(SaltUtil.getUUId());
                roleResource.setRoleid(roleId);
                roleResource.setResourceid(resourceId);
                list.add(roleResource);
			}
			int i=roleResourceMapper.insertBatch(list);
			if(i>=1) {
				map.put("status", true);
				map.put("msg", "角色授权成功。");
			}else {
				map.put("status", false);
				map.put("msg", "角色授权失败。");
			}
		}else{
			map.put("status", true);
			map.put("msg", "角色授权成功。");
		}
		return map;
    }
}
