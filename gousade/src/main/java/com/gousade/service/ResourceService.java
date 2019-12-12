package com.gousade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gousade.mapper.ResourceMapper;
import com.gousade.pojo.Resource;
import com.gousade.pojo.Tree;

@Service
public class ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;
	
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
            tree.setState(resource.getStatus());
            trees.add(tree);
        }
        return trees;
    }
}
