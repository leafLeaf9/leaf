package com.gousade.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gousade.annotation.OperationRecord;
import com.gousade.mapper.OperationRecordLogMapper;
import com.gousade.pojo.OperationRecordLog;
import com.gousade.pojo.User;

import lombok.extern.slf4j.Slf4j;

//AOP 切面
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {
	
	@Autowired
	private OperationRecordLogMapper operationRecordLogMapper;
	
	@Pointcut(value = "@annotation(com.gousade.annotation.OperationRecord)")
	private void pointcut() {

	}
	
	@AfterReturning(value = "pointcut() && @annotation(operationRecord)", returning = "result")
	public void logAfterReturning(JoinPoint point,Object result,OperationRecord operationRecord) {
		User user =null;
		if (SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null) {
			user=null;
		}else{
			user=(User) SecurityUtils.getSubject().getPrincipal();
			Object[] params = point.getArgs();
			StringBuilder stringBuilder = new StringBuilder();
			for (Object param : params) {
                stringBuilder.append(param.toString());
                stringBuilder.append(",");
            }
			String operationParam = stringBuilder.toString().substring(0, stringBuilder.length()-1);
			log.info("操作人："+user.getUserName()+",调用接口："+point.getSignature().getDeclaringTypeName()+"."
			+point.getSignature().getName()
			+",接口序号:"+operationRecord.operationNum()+",接口描述："+operationRecord.operationDescription()
			+",参数："+operationParam+"。");
			OperationRecordLog operationRecordLog = new OperationRecordLog();
			operationRecordLog.setOperationPerson(user.getUserName());
			operationRecordLog.setOperationNum(operationRecord.operationNum());
			operationRecordLog.setOperationDescription(operationRecord.operationDescription());
			operationRecordLog.setOperationInterface(point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName());
			operationRecordLog.setOperationParam(operationParam);
			operationRecordLogMapper.insert(operationRecordLog);
		}
	}
}

