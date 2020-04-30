package com.gousade.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gousade.pojo.Role;
import com.gousade.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/selectRoleList",method=RequestMethod.POST)
	public Map<String,Object> selectRoleList(@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String name = request.getParameter("name");
		param.put("name",name);
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
		List<Role> list = roleService.selectRoleList(param);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		long total = pageInfo.getTotal();
		result.put("rows", list);
		result.put("total", total);
		return result;
	}
	
	@RequestMapping(value="/saverole",method=RequestMethod.POST)
	public Map<String,Object> saverole(@RequestBody Role role) throws IOException{
		if(StringUtils.isBlank(role.getId())){
			return roleService.insertrole(role);
		}else{
			return roleService.updaterole(role);
		}		
	}
	
	@RequestMapping(value="/deleterole",method=RequestMethod.POST)
	public Map<String,Object> deleterole(String[] ids){
		List<String> list=Arrays.asList(ids);
		return roleService.deleteBatchIds(list);
	}
	
	@RequestMapping(value="/selectResourceIdListByRoleId",method=RequestMethod.POST)
	public Map<String,Object> selectResourceIdListByRoleId(@RequestBody Role role){
		String id =  role.getId();
		return roleService.selectResourceIdListByRoleId(id);
	}
	
	@RequestMapping(value="/saveRoleAuthorize",method=RequestMethod.POST)
	public Map<String,Object> saveRoleAuthorize(String roleId, String[] resourceIds){
		return roleService.saveRoleAuthorize(roleId,resourceIds);
	}
}
