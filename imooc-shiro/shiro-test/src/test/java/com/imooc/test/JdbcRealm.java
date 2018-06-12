package com.imooc.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealm {
    DruidDataSource dataSource=new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("");
    }

    @Test
    public void testAuthentication() {
        org.apache.shiro.realm.jdbc.JdbcRealm jdbcRealm=new org.apache.shiro.realm.jdbc.JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setAuthenticationQuery("select password from user where name = ? ");
        jdbcRealm.setUserRolesQuery("select role from userrole where name = ?");
        jdbcRealm.setPermissionsQuery("select permission from perm where rolename = ?");
//        IniRealm iniRealm=new IniRealm("classpath:user.ini");
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        UsernamePasswordToken token = new UsernamePasswordToken("li", "123");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(subject.isAuthenticated());
//        System.out.println(subject.hasRole("delete"));
        System.out.println(subject.hasRole("student"));
//        System.out.println(subject.hasRole("admin"));
//        System.out.println(subject.checkPermission("delete"));
        subject.checkPermission("ad");
    }
}
