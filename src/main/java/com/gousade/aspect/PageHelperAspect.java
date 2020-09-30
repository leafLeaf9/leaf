package com.gousade.aspect;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gousade.utils.DataTablesPageUtil;
import com.gousade.utils.DataTablesResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(1)
@Component
@Aspect
public class PageHelperAspect {

	/**
	 * 定义一个方法 声明切点表达式 使用注解@pointcut
	 * <p>
	 * execution(* com.text.aop.*.*(..))
	 */

	@Pointcut("execution(com.gousade.utils.DataTablesPageUtil com.gousade.service.*.*(com.gousade.utils.DataTablesPageUtil))")
	private void pointCutPagination() {
	}

	// 声明环绕通知
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Around("pointCutPagination()")
	public Object process(ProceedingJoinPoint point) throws Throwable {
		log.info("PageHelper AOP Pagination start.");
		// 访问目标方法的参数：
		Object[] args = point.getArgs();
		DataTablesPageUtil dataTable = null;
		if (args != null && args.length > 0 && args[0].getClass() == DataTablesPageUtil.class) {
			dataTable = (DataTablesPageUtil) args[0];
			PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
		}
		// 用改变后的参数执行目标方法
		DataTablesPageUtil returnValue = (DataTablesPageUtil) point.proceed(args);
		PageInfo pageInfo = new PageInfo(returnValue.getData());
		// 封装数据给DataTables
		dataTable = DataTablesResultUtil.packageResult(dataTable, pageInfo);
		// System.out.println("@Around：执行目标方法之后...");
		// System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
		log.info("PageHelper AOP Pagination succeeded.");
		return dataTable;
	}

	/*@Before("pointCutMethod()")
	public void permissionCheck(JoinPoint point) {
		System.out.println("@Before：模拟权限检查...");
		System.out.println("@Before：目标方法为：" +
				point.getSignature().getDeclaringTypeName() +
				"." + point.getSignature().getName());
		System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
	}*/

	/*@AfterReturning(pointcut = "pointCutMethod()", returning = "returnValue")
	public void log(JoinPoint point, Object returnValue) {
		System.out.println("@AfterReturning：模拟日志记录功能...");
		System.out.println("@AfterReturning：目标方法为：" +
				point.getSignature().getDeclaringTypeName() +
				"." + point.getSignature().getName());
		System.out.println("@AfterReturning：参数为：" +
				Arrays.toString(point.getArgs()));
		System.out.println("@AfterReturning：返回值为：" + returnValue);
		System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
	}*/

	/*@After("pointCutMethod()")
	public void releaseResource(JoinPoint point) {
		System.out.println("@After：模拟释放资源...");
		System.out.println(
				"@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@After：被织入的目标对象为：" + point.getTarget());
	
	}*/
}
