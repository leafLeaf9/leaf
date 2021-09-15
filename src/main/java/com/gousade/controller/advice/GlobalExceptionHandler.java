package com.gousade.controller.advice;

import com.gousade.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseResult> handleException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError fieldError = result.getFieldError();
        String message;
        if (fieldError != null) {
            message = String.format("%s。%s.%s：%s", fieldError.getDefaultMessage(),
                    fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue());
        } else {
            List<ObjectError> errors = result.getAllErrors();
            message = errors.get(0).getDefaultMessage();
        }
        ResponseResult responseResult = ResponseResult.renderError().message(message);
        log.error("发生参数校验异常", e);
        return new ResponseEntity<>(responseResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e, HttpServletResponse response) {
        log.error("发生未知异常", e);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ResponseResult.renderError().message("发生未知异常，请联系系统管理员。");
    }

}
