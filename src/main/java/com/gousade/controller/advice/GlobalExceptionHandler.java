package com.gousade.controller.advice;

import com.gousade.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-12 8:53:01
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param e exception
	 * @return 文件异常
	 */
	@ResponseBody
	@ExceptionHandler(MultipartException.class)
	public Object handleMultipartException(MultipartException e) {
		log.warn(e.getMessage());
		return ResponseResult.renderError().message("发生文件异常：" + e.getCause().getMessage());
	}

	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	public Object handleUnauthorizedException(UnauthorizedException e) {
		return ResponseResult.renderError().message("发生权限异常：无权限。" + e.getCause().getMessage());
	}

	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	public Object handleAuthorizationException(AuthorizationException e) {
		return ResponseResult.renderError().message("发生权限异常：未认证。" + e.getCause().getMessage());
	}

	@ResponseBody
	@ExceptionHandler(IOException.class)
	public ResponseResult handleIOException(IOException e) {
		log.error("发生IO异常", e);
		return ResponseResult.renderError().message("发生IO异常：" + e.getCause().getMessage());
	}

	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	public ResponseResult handleNullPointerException(NullPointerException e) {
		log.error("发生空指针异常", e);
		return ResponseResult.renderError().message("发生空指针异常：" + e.getCause().getMessage());
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e, HttpServletResponse response) {
		log.error(e.getMessage(), e);
//		e.printStackTrace();
		response.setContentType("text/html;charset=utf-8");
		return ResponseResult.renderError().message("发生未知异常：" + e.getMessage());
	}

}
