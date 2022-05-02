package com.security.AuthenticationDemo.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("tej").password("{noop}tej").authorities("ADMIN");
        auth.inMemoryAuthentication().withUser("std1").password("{noop}std1").authorities("STUDENT");
        auth.inMemoryAuthentication().withUser("employ").password("{noop}employ").authorities("EMPLOYEE");
    }

    @Override
    protected  void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests()
        //URL
                /*
                antMatchers(path):
this method used to compare request URL(path) with
Controller#method URL(Path), if they are matched then its connected
authority type[permitAll()/authenticated()/hasAuthority()] is selected.
                 */
        .antMatchers("/home").permitAll()
        .antMatchers("/welcome").authenticated()
        .antMatchers("/admin").hasAuthority("ADMIN")
        .antMatchers("/emp").hasAuthority("EMPLOYEE")
        .antMatchers("/std").hasAuthority("STUDENT")
        .antMatchers("/common").hasAnyAuthority("ADMIN","EMPLOYEE")
        .anyRequest().authenticated()
        //login
        .and()
                .formLogin()
                .defaultSuccessUrl("/welcome",true)

        //logout
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

        //exception details
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied");
    }
}
