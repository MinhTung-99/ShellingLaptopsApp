package com.shelling.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
        .antMatchers("/laptoppage").authenticated()
        .antMatchers("/newlaptop").authenticated()
        .antMatchers("/editlaptop/{id}").authenticated()
        .antMatchers("/deletelaptop/{id}").authenticated()
        .antMatchers("/orderpage").authenticated()
        .antMatchers("/deleteorder/{id}").authenticated()
        .and()
        .formLogin();
		
		http.csrf().disable();
	}
}
