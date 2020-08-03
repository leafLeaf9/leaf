package com.gousade.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.gousade.annotation.OperationRecord;
import com.gousade.pojo.User;

import lombok.extern.slf4j.Slf4j;

//AOP 切面
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {
	@Pointcut(value = "@annotation(com.gousade.annotation.OperationRecord)")
	private void pointcut() {

	}
	
	@SuppressWarnings("unused")
	@AfterReturning(value = "pointcut() && @annotation(operationRecord)", returning = "result")
	public void logAfterReturning(JoinPoint point,Object result,OperationRecord operationRecord) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		// HttpSession session = request.getSession();
		User sUser =null;
		if (SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null) {
			sUser=null;
		}else{
			sUser=(User) SecurityUtils.getSubject().getPrincipal();
			String userName = sUser.getUserName();
			log.info("调用接口为"+point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName()+
			",接口序号:"+operationRecord.operationNum()+",用途:"+operationRecord.operationMethodName()+"。");
//			ExportLog log = new ExportLog();
//			log.setEx_username(userName);
//			log.setEx_module(operationRecord.operateType());
//			CallServiceUtil.callDataService("exportLogService", "insert",
//					new Object[] { log }, new Class[] { ExportLog.class });// 记录导出日志
		}
	}
}

