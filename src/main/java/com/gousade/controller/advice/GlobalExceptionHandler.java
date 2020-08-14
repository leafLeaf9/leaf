package com.gousade.controller.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import com.gousade.controller.common.BaseController;

import lombok.extern.slf4j.Slf4j;

/**
* @author woxigsd@gmail.com
* @date 2020-8-12 8:53:01
* 类说明：全局异常处理
*/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends BaseController{
	
	/**
	 * @param e
	 * @return
	 * 文件异常
	 */
	@ResponseBody
	@ExceptionHandler(MultipartException.class)
    public Object handleMultipartException(MultipartException e) {
		log.warn(e.getMessage());
        return renderError("发生文件异常："+e.getCause().getMessage());
    }
	
	@ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Object handleUnauthorizedException(UnauthorizedException e) {
        return renderError("发生权限异常：无权限。"+e.getCause().getMessage());
    }
	
	@ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e) {
		return renderError("发生权限异常：未认证。"+e.getCause().getMessage());
    }
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
		e.printStackTrace();
        return renderError("发生未知异常："+e.getMessage());
    }

}
