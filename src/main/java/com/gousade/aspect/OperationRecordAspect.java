package com.gousade.aspect;

import com.gousade.annotation.OperationRecord;
import com.gousade.log.parse.LogRecordExpressionEvaluator;
import com.gousade.mapper.OperationRecordLogMapper;
import com.gousade.pojo.OperationRecordLog;
import com.gousade.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.IntStream;

//AOP 切面
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {

    private final LogRecordExpressionEvaluator expressionEvaluator = new LogRecordExpressionEvaluator();

    @Autowired
    private OperationRecordLogMapper operationRecordLogMapper;


    @Pointcut(value = "@annotation(com.gousade.annotation.OperationRecord)")
    private void pointcut() {
    }

    @AfterReturning(value = "pointcut() && @annotation(operationRecord)")
//    @AfterThrowing(value = "pointcut() && @annotation(operationRecord)")
//    @Before(value = "pointcut() && @annotation(operationRecord)")
    public void log(JoinPoint point, OperationRecord operationRecord) {
        User user;
        if (SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null) {
            user = User.builder().id("guest").userName("guest").build();
        } else {
            user = (User) SecurityUtils.getSubject().getPrincipal();
        }

        String actualDescription = processTemplate(point, operationRecord.operationDescription());

        Object[] params = point.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object param : params) {
            stringBuilder.append(param.toString());
            stringBuilder.append(",");
        }

        String operationParam = stringBuilder.length() > 0 ? stringBuilder.substring(0, stringBuilder.length() - 1) : null;
        log.info("操作人：" + user.getUserName() + ",调用接口：" + point.getSignature().getDeclaringTypeName() + "."
                + point.getSignature().getName() + ",接口序号:" + operationRecord.operationNum() + ",接口描述："
                + actualDescription + ",参数：" + operationParam + "。");
        OperationRecordLog operationRecordLog = OperationRecordLog.builder().operationPerson(user.getUserName())
                .operationNum(operationRecord.operationNum())
                .operationDescription(operationRecord.operationDescription())
                .operationInterface(
                        point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName())
                .operationParam(operationParam).build();
        operationRecordLogMapper.insert(operationRecordLog);
    }

    private String processTemplate(JoinPoint point, String conditionExpression) {
        Object target = point.getTarget();
        Class<?> targetClass = target.getClass();
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(method, args, targetClass);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        return (String) expressionEvaluator.parseExpression(conditionExpression, methodKey, evaluationContext);

    }

    /**
     * 此方法只支持#变量名[.字段名] 不支持p0 p1[.字段名]
     */
    private String getParameter(JoinPoint point, String conditionExpression) {
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //使用SpringEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SpringEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SpringEL上下文中
        IntStream.range(0, Objects.requireNonNull(paraNameArr).length)
                .forEach(i -> context.setVariable(paraNameArr[i], args[i]));
        return parser.parseExpression(conditionExpression).getValue(context, String.class);
    }
}
