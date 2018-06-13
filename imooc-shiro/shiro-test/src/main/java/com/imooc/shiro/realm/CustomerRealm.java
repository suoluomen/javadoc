package com.imooc.shiro.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;
//自定义授权、认证
public class CustomerRealm extends AuthorizingRealm {
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName= (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles=getRoles(userName);
        Set<String> permission=getPermission(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(permission);

        return simpleAuthorizationInfo;
    }

    private Set<String> getPermission(String userName) {
        Set<String> sets=new  HashSet<String>();
        sets.add("add");
        sets.add("delete");
        return sets;
    }

    private Set<String> getRoles(String userName) {
        Set<String> sets=new  HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        String password=getPassword(userName);
        SimpleAuthenticationInfo simpleAuthorizationInfo=new SimpleAuthenticationInfo(userName,password,"customerRealm");
        /*加盐*/
        simpleAuthorizationInfo.setCredentialsSalt(ByteSource.Util.bytes("123"));
        return simpleAuthorizationInfo;
    }

    private String getPassword(String userName) {
        return "4297f44b13955235245b2497399d7a93";
    }
    public static void main(String[] args){
        Md5Hash md5Hash=new Md5Hash("123","123");
        System.out.print(md5Hash);
    }
}
