package cn.ucmed.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro配置
 * 有哪些url是需要拦截的，哪些是不需要拦截的，登录页面、登录成功页面的url、自定义的Realm等这些信息需要设置到Shiro中
 */
@Configuration
public class ShiroConfig {

    /**
     * 单机环境，session交给shiro管理
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager sessionManager(){
        long globalSessionTimeout = 3600L;
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionValidationInterval(globalSessionTimeout * 1000);
        sessionManager.setGlobalSessionTimeout(globalSessionTimeout * 1000);
        return sessionManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setSuccessUrl("/admin/login/index");
        shiroFilterFactoryBean.setLoginUrl("/admin/login/logout");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:url必须认证通过才可以访问; anon:url可以匿名访问
        filterChainDefinitionMap.put("/admin/login","anon");
        filterChainDefinitionMap.put("/admin/news/**","anon");
        filterChainDefinitionMap.put("/admin/css/**", "anon");
        filterChainDefinitionMap.put("/admin/face/**", "anon");
        filterChainDefinitionMap.put("/admin/js/**", "anon");
        filterChainDefinitionMap.put("/admin/img/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/admin/lay/**", "anon");
        filterChainDefinitionMap.put("/admin/libs/**", "anon");
        filterChainDefinitionMap.put("/FontIcoList.html", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/admin/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/admin/swagger", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/admin/login/logout", "logout");
        filterChainDefinitionMap.put("/admin/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getUserRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
