package org.factoriaf5.bftp2project4grupo5;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/", "/games/", "/assets/**","/styles/**", "/login").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().permitAll()
                    .and().logout().permitAll();
        }
    }

