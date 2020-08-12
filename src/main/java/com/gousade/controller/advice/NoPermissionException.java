package com.gousade.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
* @author 作者: woxi-Gisard
* @version 创建时间:2019年12月7日 下午5:31:52 
* 类说明:异常处理类
*/
@ControllerAdvice
public class NoPermissionException {
	@ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Map<String, Object> handleShiroException(Exception ex) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", false);
        res.put("msg", "无权限!");
        return res;
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "权限认证失败";
    }
}
