package com.gousade.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		HttpSession session = request.getSession();	
		String users =  (String) session.getAttribute("u");
		if(users==null){
			logger.warn("当前无session");
		}else{			
			logger.info("当前有session:"+users);
		}
		User obj = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj == null) {       	
        	logger.warn("当前shiro无subject");
            return "login";
        }
        logger.info("当前shiro-subject:"+obj.toString());
		return "redirect:/main";
	}
	
	@RequestMapping("/{pageName:^(?!favicon.ico).*$}")//此正则用于排除@RequestMapping("favicon.ico")将favicon.ico也解析为jsp视图
	public String toPage(@PathVariable String pageName,HttpServletRequest request,HttpServletResponse response){
			return pageName;
	}
	
	/*@RequestMapping("/favicon.ico")
    public String returnFavicon() {
        return "forward:/static/favicon.ico";
        //此方法有错误 不知道应该返回什么才能得到favicon.ico图标 不写此方法的话@RequestMapping("/{pageName}"默认会把获取favicon.ico得请求也解析为jsp视图
    }*/

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
