package com.gousade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gousade.mapper.ResourceMapper;
import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.UserRoleMapper;
import com.gousade.pojo.Resource;
import com.gousade.pojo.Tree;
import com.gousade.pojo.User;
import com.gousade.utils.SaltUtil;

@Service
public class ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	public List<Resource> selectTree(User user) {
		List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(user.getId());
        if (roleIdList == null || roleIdList.size() == 0) {
            return null;
        }
        List<Resource> resourceLists = roleMapper.findResourcesByRoleIds(roleIdList);
        if (resourceLists == null || resourceLists.size() == 0) {
            return null;
        }
        return resourceLists;
	}
	
	public List<Resource> selectResourceList() {
        return resourceMapper.selectResourceList();
    }
	
	public List<Tree> selectAllTree() {
        // 获取所有的资源 tree形式，展示
        List<Tree> trees = new ArrayList<Tree>();
        List<Resource> resources = selectResourceList();
        if (resources == null) {
            return trees;
        }
        for (Resource resource : resources) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            tree.setState("1");
            trees.add(tree);
        }
        return trees;
    }

	public Map<String, Object> insertresource(Resource resource) {
		Map<String, Object> map = new HashMap<String, Object>();
		resource.setId(SaltUtil.getUUId());
		int i=resourceMapper.insert(resource);
		if(i>=1) {
			map.put("status", true);
			map.put("msg", "新增资源成功");
		}else {
			map.put("status", false);
			map.put("msg", "新增资源失败");
		}
		return map;
	}
	
	public Map<String, Object> updateresource(Resource resource) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i=resourceMapper.updateByPrimaryKey(resource);
		if(i>=1) {
			map.put("status", true);
			map.put("msg", "编辑资源成功");
		}else {
			map.put("status", false);
			map.put("msg", "编辑资源失败");
		}
		return map;
	}
	
	public Map<String, Object> deleteBatchIds(List<? extends String> idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i=resourceMapper.deleteBatchIds(idList);
		if(i>=1) {
			map.put("status", true);
			map.put("msg", "删除资源成功");
		}else {
			map.put("status", false);
			map.put("msg", "删除资源失败");
		}
		return map;
	}

}
