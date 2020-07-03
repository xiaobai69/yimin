package cn.com.shiro;


import cn.com.entity.Roleuser;
import cn.com.service.IRoleuserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IRoleuserService service;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        Roleuser roleuser = (Roleuser) subject.getPrincipal();
        Roleuser dbRoleuser = service.selByName(roleuser.getRoleName());
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给资源添加角色
        info.addRole(dbRoleuser.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * AuthenticationToken   主体传过来的认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证逻辑......");


        //编写shiro判断逻辑，判断用户名和密码
        //获取主体的姓名
        String userName = (String) authenticationToken.getPrincipal();
        Roleuser roleuser = service.selByName(userName);
        if(roleuser == null){
            //用户名不存在
            //shiro底层会抛出UnKnowAccountException
            return null;
        }

        //自定义盐值
        ByteSource salt = ByteSource.Util.bytes(roleuser.getRoleName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                roleuser,//安全数据
                roleuser.getRolePassword(),//密码
                salt,
                getName()
        );

        //判断密码
        return authenticationInfo;
    }



}
