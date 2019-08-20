package com.gousade.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.gousade.pojo.Menu;
import com.gousade.pojo.Role;
import com.gousade.pojo.User;
import com.gousade.service.RoleService;
import com.gousade.service.UserService;

@Controller
//添加restcontroller注解之后，return"main"不能再返回main.jsp，需要改写成ModelAndView mv = new ModelAndView("main"); return mv;

@RestController
public class RoleController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 查询角色列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/queryrolelist",method=RequestMethod.POST)
	public List<Map<String,Object>> queryuserlist(@RequestBody Map<String,Object> map){
		
		return roleService.queryrolelist(map);
	}
	
	@RequestMapping(value="/testrolelist",method=RequestMethod.POST)
	public List<Role> testrolelist(@RequestBody Map<String,Object> map){
		List<Role> list=roleService.testrolelist(map);
		return roleService.testrolelist(map);
	}
	
	/**
	 * 新增角色
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/insertrole",method=RequestMethod.POST)
	public Map<String,Object> insertrole(@RequestBody Map<String,Object> map){
		
		return roleService.insertrole(map);
	}
	
	
	/**
	 * 根据ID修改角色信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/updaterole",method=RequestMethod.POST)
	public Map<String,Object> updateuser(@RequestBody Map<String,Object> map){
		
		return roleService.updaterole(map);
	}
	
	/**
	 * 根据ID删除角色信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/delroles",method=RequestMethod.POST)
	public Map<String,Object> delroles(@RequestBody Map<String,Object> map){
		
		return roleService.delroles(map);
	}
	
	
	/**
	 * 查询菜单列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/querymenulist",method=RequestMethod.POST)
	public List<Map<String,Object>> querymenulist(@RequestBody Map<String,Object> map){
		
		return roleService.querymenulist(map);
	}
	
	/**
	 * 根据ID修改菜单信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/updatemenu",method=RequestMethod.POST)
	public Map<String,Object> updatemenu(@RequestBody Map<String,Object> map){
		
		return roleService.updatemenu(map);
	}
	
	/**
	 * 根据ID删除角色信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/delmenus",method=RequestMethod.POST)
	public Map<String,Object> delmenus(@RequestBody Map<String,Object> map){
		
		return roleService.delmenus(map);
	}
}
