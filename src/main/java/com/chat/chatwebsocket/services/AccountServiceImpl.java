package com.chat.chatwebsocket.services;


import com.chat.chatwebsocket.dao.RoleRepository;
import com.chat.chatwebsocket.dao.UserRepository;
import com.chat.chatwebsocket.model.AppRole;
import com.chat.chatwebsocket.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ahmed on 07/04/2018.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser appUser) {
        String hashPW=bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(hashPW);
        return userRepository.save(appUser);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String usernom, String rolenom) {
        AppRole role=roleRepository.findByRole(rolenom);
        AppUser appUser =userRepository.findBynom(usernom);
        appUser.getRole().add(role);
    }

    @Override
    public AppUser findUserBynom(String nom) {
        return userRepository.findBynom(nom);
    }
}
