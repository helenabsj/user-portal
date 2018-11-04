package com.arens.userportal.config;

import com.arens.userportal.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan("com.arens.userportal.security")
public class JavaSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
     private MyUserDetailsService myUserDetailsService;

     @Autowired
     public void configureGlobalAuth(final AuthenticationManagerBuilder auth) throws  Exception{


             auth.userDetailsService(myUserDetailsService);



     }

     @Bean
     public PasswordEncoder encoder() {
         return new BCryptPasswordEncoder();
     }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .anyRequest()
                //.antMatchers("/v1/**")
                //anonymous()
                //fullyAuthenticated - similar to authenticated - remember me style of access does not work.
                //denyAll - no one has access to this URL - in case you need to close
                //hasAnyAuthority("ROLE_SOMETHING")  -- has a role
                //hasAuthority ("ROLE_SOMETHING") -- for only one role.
                //access("hasRole(ROLE_SOMEHTING)" -- spring expressions is a bit more flexible
                .permitAll()
                /*
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();

    }
}
