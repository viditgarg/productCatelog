package com.vk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true )
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser( "user" ).password( "welcome" ).roles( "USER" ).and()
            .withUser( "admin" ).password( "password" ).roles( "USER", "ADMIN" );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.httpBasic().and()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
            .authorizeRequests().antMatchers("/**").hasRole( "USER" );
    }
}