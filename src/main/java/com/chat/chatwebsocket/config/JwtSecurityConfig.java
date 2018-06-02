package com.chat.chatwebsocket.config;


import com.chat.chatwebsocket.security.JWTAuthenticationFilter;
import com.chat.chatwebsocket.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity

public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }
	@Override
    protected void configure(HttpSecurity security) throws Exception
    {
     security.httpBasic().disable();
     security.csrf().disable();//annuler l'utilisation de csrf (csrf: c,est un password qui utilise spring pour s'authentifier )
     security.formLogin();
     security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//deactiver l'implementation du token dans la ssesion
     security.authorizeRequests().antMatchers(HttpMethod.POST,"/**").permitAll();//(permter que au  Role ADMIN accese a /user avec post)
     security.authorizeRequests().antMatchers(HttpMethod.POST,"/login/**").permitAll();
     security.authorizeRequests().antMatchers(HttpMethod.GET,"/user/**").permitAll();
     security.addFilter(new JWTAuthenticationFilter(authenticationManager()));
     security.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
     //security.authorizeRequests().anyRequest().authenticated();*/
    }


}
