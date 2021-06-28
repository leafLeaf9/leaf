package com.gousade.controller.advice;

import com.gousade.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-12 8:53:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param e exception
	 * @return 文件异常
	 */
	@ExceptionHandler(MultipartException.class)
	public Object handleMultipartException(MultipartException e) {
		log.error("发生文件异常", e);
		return ResponseResult.renderError().message("发生文件异常：" + e.getCause().getMessage());
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseResult handleUnauthorizedException(UnauthorizedException e) {
		log.error("发生权限异常", e);
		return ResponseResult.renderError().message("发生权限异常：无权限。" + e.getCause().getMessage());
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseResult handleAuthorizationException(AuthorizationException e) {
		log.error("发生权限异常", e);
		return ResponseResult.renderError().message("发生权限异常：未认证。" + e.getCause().getMessage());
	}

	@ExceptionHandler(IOException.class)
	public ResponseResult handleIOException(IOException e) {
		log.error("发生IO异常", e);
		return ResponseResult.renderError().message("发生IO异常：" + e.getCause().getMessage());
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseResult handleNullPointerException(NullPointerException e) {
		log.error("发生空指针异常", e);
		return ResponseResult.renderError().message("发生空指针异常：" + e.getCause().getMessage());
	}

	@ExceptionHandler(SQLException.class)
	public ResponseResult handleSQLException(SQLException e) {
		log.error("发生sql异常", e);
		return ResponseResult.renderError().message("发生数据库异常，请联系系统管理员。");
	}

	@ExceptionHandler(Exception.class)
	public ResponseResult handleException(Exception e) {
		log.error("发生未知异常", e);
		return ResponseResult.renderError().message("发生未知异常，请联系系统管理员。");
	}

}
