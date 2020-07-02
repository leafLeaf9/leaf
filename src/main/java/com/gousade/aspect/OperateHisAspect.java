package com.gousade.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.gousade.annotation.OperateHis;
import com.gousade.pojo.User;

import lombok.extern.slf4j.Slf4j;

//AOP 切面
@Aspect
@Component
@Slf4j
public class OperateHisAspect {
	@Pointcut(value = "@annotation(com.gousade.annotation.OperateHis)")
	private void pointcut() {

	}
	
	@AfterReturning(value = "pointcut() && @annotation(operateHis)", returning = "result")
	public void afterReturning(JoinPoint point,Object result,OperateHis operateHis) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		// HttpSession session = request.getSession();
		User sUser =null;
		if (SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null) {
			sUser=null;
		}else{
			sUser=(User) SecurityUtils.getSubject().getPrincipal();
			String userName = sUser.getUserName();
			log.info("操作方法序号"+operateHis.operationType()+"已记录");
//			ExportLog log = new ExportLog();
//			log.setEx_username(userName);
//			log.setEx_module(operateHis.operateType());
//			CallServiceUtil.callDataService("exportLogService", "insert",
//					new Object[] { log }, new Class[] { ExportLog.class });// 记录导出日志
		}
	}
}

