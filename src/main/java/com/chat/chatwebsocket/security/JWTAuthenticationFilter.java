package com.chat.chatwebsocket.security;


import com.chat.chatwebsocket.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ahmed on 07/04/2018.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AppUser appUser=null;

        try {
            appUser=new ObjectMapper().readValue(request.getInputStream(),AppUser.class);//permet de prondre des donne json et les stock dans un objet java(objetMapper)

        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("*********************************************************");
        System.out.println(appUser.getNom());
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getNom(),appUser.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User springUser=(User) authResult.getPrincipal();
        String jwt= Jwts.builder().setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
                .claim("roles",springUser.getAuthorities())
                .compact();
        System.out.println("*********************************************************");
        System.out.println(jwt);
        response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+jwt);
        //super.successfulAuthentication(request, response, chain, authResult);
    }
}
