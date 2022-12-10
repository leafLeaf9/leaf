package com.gousade.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用shiro-spring-boot-starter会报缺失bean的错误，所以还是使用shiro-spring 1.7.1
 */
@Slf4j
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        log.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(this.buildFilterChainDefinitionMap());
        return shiroFilterFactoryBean;
    }

    private Map<String, String> buildFilterChainDefinitionMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        // 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        map.put("/login", "anon");
        map.put("/regist", "anon");
		/*map.put("/redis/**", "anon");
		map.put("/jasypt/**", "anon");*/
        map.put("/favicon.ico", "anon");// 防止favicon.ico图标被拦截
        map.put("/admin/smsCode/**", "anon");
		map.put("/admin/gousadeTest/**", "anon");
		map.put("/admin/goCqHttpRobot/**", "anon");
		map.put("/admin/miHoYo/**", "anon");
		map.put("/admin/sysUser/ShiroRegist", "anon");
		map.put("/admin/sysUser/loginShiroUser", "anon");
		map.put("/", "anon");
		/*map.put("/index", "anon");*/
		map.put("/static/**", "anon");
		/*map.put("/template/**", "anon");*/
		// 下面四条都是swagger3过滤
		map.put("/swagger-ui/**", "anon");
		map.put("/webjars/**", "anon");
		map.put("/swagger-resources/**", "anon");
		map.put("/v3/api-docs", "anon");
		map.put("/captcha/chiralCarbon/**", "anon");
		map.put("/**", "authc");
		return map;
	}

    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(52);// 散列的次数，比如散列两次，相当于 md5(md5(""));
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean
    public ShiroRealm myShiroRealm() {
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());// 告诉realm,使用credentialsMatcher加密算法类来验证密文
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 开启shiro aop注解支持.--开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 使用代理方式;所以需要开启代码支持; 配置以下bean即可实现此功能
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

	/*@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver
	createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "/base/databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException","/403");
		r.setExceptionMappings(mappings);  // None by default
		//r.setDefaultErrorView("error");    // No default
		r.setDefaultErrorView("/login");    // 访问错误，直接访问登录页面
		r.setExceptionAttribute("ex");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}*/
}