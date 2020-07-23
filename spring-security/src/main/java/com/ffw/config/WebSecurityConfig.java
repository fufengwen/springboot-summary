package com.ffw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description 配置授权规则
 * @Author fufengwen
 * @Time 2020/6/30 10:13
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 拦截路径规则，对于/或/home不拦截，其余重定向到/login
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
            .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//指重定向跳转的页面
                .permitAll()
                .and()
                .logout()
                .permitAll();*/
        http.authorizeRequests()     // 对请求进行验证
                .antMatchers("/visitor/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")     // 必须有ADMIN权限
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")       //有任意一种权限
                .anyRequest()     //任意请求（这里主要指方法）
                .authenticated()   //// 需要身份认证
                .and()   //表示一个配置的结束
                .formLogin().permitAll()  //开启SpringSecurity内置的表单登录，会提供一个/login接口
                .and()
                .logout().permitAll()  //开启SpringSecurity内置的退出登录，会为我们提供一个/logout接口
                .and()
                .csrf().disable();    //关闭csrf跨站伪造请求
    }

    /**
     * 定义全局内存变量，预先放一个user，账号密码为admin 123456 角色为USER
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER");
    }
}
