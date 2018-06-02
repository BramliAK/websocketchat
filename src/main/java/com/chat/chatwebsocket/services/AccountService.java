package com.chat.chatwebsocket.services;


import com.chat.chatwebsocket.model.AppRole;
import com.chat.chatwebsocket.model.AppUser;

/**
 * Created by ahmed on 07/04/2018.
 */
public interface AccountService {
    public AppUser saveUser(AppUser appUser);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String usernom, String rolenom);
    public AppUser findUserBynom(String nom);
}
