package com.chat.chatwebsocket.controllers;

import com.chat.chatwebsocket.dao.UserRepository;
import com.chat.chatwebsocket.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUsersController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public List<AppUser> all (final String nom) {
        return userRepository.findAll();

    }
}
