package com.gousade.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gousade.controller.common.BaseController;

/**
* @author woxigsd@gmail.com
* @date 2020-8-12 8:53:01
* 
*/
@ControllerAdvice
public class GlobalExceptionHandler extends BaseController{
	
	@ResponseBody
	@ExceptionHandler(MultipartException.class)
    public Object handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return renderError("上传失败："+e.getCause().getMessage());
    }

}
