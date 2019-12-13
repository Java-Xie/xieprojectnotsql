package com.chu.xieproject.util;

import com.chu.xieproject.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //模拟数据库：前提条件是有绑定权限字符串
        Subject subject  = SecurityUtils.getSubject();
        Users u = (Users)subject.getPrincipal();
        System.out.println(u.getPermission());
        simpleAuthorizationInfo.addStringPermission(u.getPermission());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //数据库中的账号和密码
        String name = "admin";
        String pwd = "123";
        //得到绑定在token中用户输入的账号与密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if (!token.getUsername().equals(name)){
            System.out.println("账号错误");
            //认证不予通过
            return null;
        }
        //从数据库得到的数据
        Users u = new Users(1,name,pwd,"user:add");
        return new SimpleAuthenticationInfo(u,pwd,"");
    }
}
