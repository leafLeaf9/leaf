package com.gousade.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
		return resourceService.selectResourceList();
	}
	
	@RequestMapping(value="/selectAllTree",method=RequestMethod.POST)
	public List<Tree> selectAllTree(){
		return resourceService.selectAllTree();
	}
	
	@RequestMapping(value="/saveresource",method=RequestMethod.POST)
	public Map<String,Object> saveresource(Resource resource){
		if(StringUtils.isBlank(resource.getId())){
			return resourceService.insertresource(resource);
		}else{
			return resourceService.updateresource(resource);
		}		
	}
	
	@RequestMapping(value="/deleteresource",method=RequestMethod.POST)
	public Map<String,Object> deleteresource(String[] ids){
		List<String> list=Arrays.asList(ids);
		return resourceService.deleteBatchIds(list);
	}
}
