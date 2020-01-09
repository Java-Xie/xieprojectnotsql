package com.chu.xieproject.config;

import com.chu.xieproject.util.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        //设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //设置拦截
        Map<String,String> filterMap = new LinkedHashMap<>();
        //filterMap.put("/index","anon");
        //filterMap.put("/update","authc");
        // filterMap.put("/insert","authc");
        //认证授权
        filterMap.put("/test","anon");
        filterMap.put("/doLogin","anon");
        filterMap.put("/register","anon");
        filterMap.put("/update","perms[user:update]");
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/*","authc");

        //没有通过认证跳转指定的页面
        //shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setLoginUrl("/test");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorizedPage");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //安全管理器
    @Bean(name="defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //授权认证的规则
    @Bean(name="userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

}
