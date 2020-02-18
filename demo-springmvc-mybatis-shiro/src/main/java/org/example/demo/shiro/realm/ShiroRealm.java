package org.example.demo.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo running "+authenticationToken);
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        //2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        log.info("从数据库中获取 username: " + username + " 所对应的用户信息.");

        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在!");
        }

        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }
        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息从数据库中获取
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = username;
        //2). credentials: 密码.
        Object credentials = null; //"fc1709d0a95a6be30bc5926fdb7f22f4";
        if("admin".equals(username)){
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if("user".equals(username)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }

        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        //4). 盐值.
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    public static void main(String[] args) {
        Object result=  new SimpleHash("MD5","123456","user",1024);
        System.out.println(result);
    }

    /**
     * 授权的时候需要实现的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo running");
        // 1 获取登陆用户信息
        Object principal = principalCollection.getPrimaryPrincipal();
        // 2 登录用户信息中获取角色  可能需要查询数据库
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(principal)){
            roles.add("admin");
        }
        //3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //4. 返回 SimpleAuthorizationInfo 对象.
        return info;
    }
}
