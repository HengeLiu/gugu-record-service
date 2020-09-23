package com.nutrition.nutritionservice.configure;

import com.nutrition.nutritionservice.service.UserAccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserAccountService userAccountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home", "/hello").permitAll()
                // .anyRequest().authenticated()
                //
                .and().formLogin().permitAll()
                //
                .and().logout().permitAll()
                //
                .and().csrf().disable();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userAccountService;
    }
}