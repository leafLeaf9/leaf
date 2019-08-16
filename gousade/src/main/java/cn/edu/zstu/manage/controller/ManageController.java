package cn.edu.zstu.manage.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageController {
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
		return "login";
	}

	@RequestMapping("/user/{pageName}")
	public String showuserlist(@PathVariable String pageName){
		return "user/"+pageName;
	}
	
}
