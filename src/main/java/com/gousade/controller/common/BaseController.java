package com.gousade.controller.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.gousade.pojo.User;

import lombok.extern.slf4j.Slf4j;

/** 
* @author 作者: woxi-Gisard
* @version 创建时间:2020年8月5日 下午10:22:09 
* 类说明:
*/
public class BaseController {
	
	protected User getShiroSessionUser() {
		if (SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null) {
			return null;
		}
		return (User) SecurityUtils.getSubject().getPrincipal();
	}
	
	public Object renderSuccess(String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", true);
		result.put("msg", msg);
		return result;
	}
	
	public Object renderError(String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", false);
		result.put("msg", msg);
		return result;
	}
	
	public Object renderBoolean(Boolean b) {
		if(b) {
			return renderSuccess("操作成功");
		}else {
			return renderError("操作失败");
		}
	}
	
}
