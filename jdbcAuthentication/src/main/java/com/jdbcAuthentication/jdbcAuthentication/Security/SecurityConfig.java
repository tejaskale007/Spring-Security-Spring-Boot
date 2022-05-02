package com.jdbcAuthentication.jdbcAuthentication.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT uname,upwd,uenabled FROM usertab where uname=?")
        .authoritiesByUsernameQuery("SELECT uname,urole FROM usertab where uname=?")
                .passwordEncoder(bCryptPasswordEncoder)
        ;
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
