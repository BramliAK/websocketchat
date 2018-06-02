package com.chat.chatwebsocket.services;


import com.chat.chatwebsocket.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ahmed on 07/04/2018.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {

        AppUser appUser =accountService.findUserBynom(nom);
        if(appUser ==null) throw new UsernameNotFoundException(nom);
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getRole().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRole()));
        });

        return new User(appUser.getNom(),appUser.getPassword(),authorities);
    }
}
