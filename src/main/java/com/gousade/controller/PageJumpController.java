package com.gousade.controller;

import com.gousade.pojo.User;
import com.gousade.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@CrossOrigin
public class PageJumpController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        User obj = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj == null) {
            log.warn("login");
            return "/login";
        }
        log.info("current subject:" + obj.getUserName());
        return "redirect:/admin/index";
    }

	/*@RequestMapping("/{pageName:^(?!favicon.ico).*$}")//此正则用于排除@RequestMapping("favicon.ico")将favicon.ico也解析为jsp视图
	public String toPage(@PathVariable String pageName,HttpServletRequest request,HttpServletResponse response){
			return pageName;
	}*/

	/*@RequestMapping("/favicon.ico")
	public String returnFavicon() {
	    return "forward:/static/favicon.ico";
	    //此方法有错误 不知道应该返回什么才能得到favicon.ico图标 不写此方法的话@RequestMapping("/{pageName}"默认会把获取favicon.ico得请求也解析为jsp视图
	}*/

    @RequestMapping("/")
    public String showIndex() {
        User obj = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj == null) {
            return "/login";
        }
        return "/admin/index";
    }

    @RequestMapping("/admin/index")
    public String main() {
        User obj = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj != null) {
            userService.updateLoginTime(obj.getId());
        }
        return "/admin/index";
    }

    @RequestMapping("/admin/{pageName}")
    public String showAdminJsp(@PathVariable String pageName) {
        return "/admin/" + pageName;
    }

    @RequestMapping("/admin/{pageType}/{pageName}")
    public String showAadminUserJsp(@PathVariable("pageType") String pageType,
                                    @PathVariable("pageName") String pageName) {
        return "/admin/" + pageType + "/" + pageName;
    }

//	@RequestMapping("/admin/role/{pageName}")
//	public String showAadminroleJsp(@PathVariable String pageName){
//		return "admin/role/"+pageName;
//	}

//	@RequestMapping("/admin/resource/{pageName}")
//	public String showAadminresourceJsp(@PathVariable String pageName){
//		return "admin/resource/"+pageName;
//	}

}
