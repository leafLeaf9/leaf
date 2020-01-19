package com.gousade.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gousade.pojo.Resource;
import com.gousade.pojo.Tree;
import com.gousade.service.ResourceService;

@RestController
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value="/selectResourceList",method=RequestMethod.POST)
	public List<Resource> selectResourceList(){
		List<Resource> r=resourceService.selectResourceList();
		return resourceService.selectResourceList();
	}
	
	@RequestMapping(value="/selectAllTree",method=RequestMethod.POST)
	public List<Tree> selectAllTree(){
		return resourceService.selectAllTree();
	}
	
	@RequestMapping(value="/insertresource",method=RequestMethod.POST)
	public Map<String,Object> insertresource(Resource resource){		
		return resourceService.insertresource(resource);
	}
}
