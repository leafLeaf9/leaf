package com.gousade.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gousade.pojo.User;
import com.gousade.service.UserService;


@Controller
public class ManageController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		HttpSession session = request.getSession();	
		String users =  (String) session.getAttribute("u");
		if(users==null){
			System.err.println("当前无session");
		}else{
			System.err.println("当前有session:"+users);
		}
		User obj = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj == null) {
        	System.err.println("当前shiro无subject");
            return "login";
        }
        System.err.println("当前shiro-subject:"+obj.toString());
		return "redirect:/main";
	}
	
	@RequestMapping("/{pageName}")
	public String toPage(@PathVariable String pageName,HttpServletRequest request,HttpServletResponse response){
		/*Set<String> notLoginPaths = new HashSet<>();
		notLoginPaths.add("");
		notLoginPaths.add("login");
		if(notLoginPaths.contains(pageName)) {
			return pageName;	
		}else {
			String user =  (String) request.getSession().getAttribute("u");
			if(user==null) {
				System.out.println("尚未登录，跳转到登录界面");
				
				return "redirect:/login";
			}else{
				return pageName;
			}
		}*/
		return pageName;
	}	

	@RequestMapping("/")
	public String showIndex(){
		User obj = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj == null) {
            return "login";
        }
		return "redirect:/main";
	}
	
	@RequestMapping("/main")
	public String main(){
		User obj = (User) SecurityUtils.getSubject().getPrincipal();
		if(obj != null){
			userService.updateLoginTime(obj.getUserId());
		}
		return "main";
	}

	@RequestMapping("/user/{pageName}")
	public String showuserlist(@PathVariable String pageName){
		return "user/"+pageName;
	}
	
}
