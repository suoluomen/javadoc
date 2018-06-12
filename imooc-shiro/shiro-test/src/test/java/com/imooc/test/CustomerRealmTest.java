package com.imooc.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.imooc.shiro.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomerRealmTest {

    @Test
    public void testAuthentication() {
        CustomerRealm customerRealm=new CustomerRealm();
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        /*加密*/
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(1);
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        defaultSecurityManager.setRealm(customerRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        UsernamePasswordToken token = new UsernamePasswordToken("li", "123");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        System.out.println(subject.hasRole("user"));
        System.out.println(subject.hasRole("delete"));
        System.out.println(subject.hasRole("admin"));
        subject.checkPermission("add");
    }
    public static void main(String[] args){
        Md5Hash md5Hash=new Md5Hash("123","123");
        System.out.print(md5Hash);
    }
}
