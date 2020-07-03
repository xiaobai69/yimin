package cn.com.shiro;
import	java.util.LinkedHashMap;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        System.out.println("拦截过滤......");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String, String> ();
        //拦截的是controller中ResquestMapper("/add")方法返回的页面，需要登录才能访问
//        filterMap.put("/html/roleIndex.html","authc");

        //对test资源放行，不用登录也可访问(要放在anthc前面执行才能生效)
        filterMap.put("/html/login.html","anon");
        filterMap.put("/html/roleLogin.html","anon");
        filterMap.put("/html/index.html","anon");

        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面(要放在anthc前面执行才能生效)
        filterMap.put("/html/roleIndex.html", "roles[admin]");

        //拦截全部资源
       // filterMap.put("/html/*","authc");


        //未登录提示页面
        shiroFilterFactoryBean.setLoginUrl("/html/roleLogin.html");


        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/html/notice.html");

        //添加Shiro内置过滤器(将过滤参数放在map集合)
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean("userRealm")
    public UserRealm getRealm()
    {
        return new UserRealm();
    }

    /**
     * 启动加密
     * @param hashedCredentialsMatcher
     * @return
     */
//    @Bean
//    public UserRealm myShiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
//            UserRealm myShiroRealm = new UserRealm();
//        //设置加密方式
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
//        return myShiroRealm;
//    }
//
//    /**
//     * 加密方式
//     * @return
//     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        //指定加密方式
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        System.out.println("加密方式：MD5");
//        //加密次数
//        credentialsMatcher.setHashIterations(1024);
//        //此处的设置，true加密用的hex编码，false用的base64编码
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){

        return new ShiroDialect();

    }
}

