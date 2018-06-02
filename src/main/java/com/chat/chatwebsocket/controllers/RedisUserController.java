package com.chat.chatwebsocket.controllers;

import com.chat.chatwebsocket.dao.RedisUserRepository;
import com.chat.chatwebsocket.dao.UserRepository;
import com.chat.chatwebsocket.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/user")
public class RedisUserController {



    @Autowired
    private RedisUserRepository redisUserRepository;

    @Autowired
    private UserRepository userRepository;


    public RedisUserController( RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }
    @GetMapping("/add/{nom}")
    public AppUser add(@PathVariable("nom") final String nom) {
        AppUser user=userRepository.findBynom(nom);
        redisUserRepository.redissave(user);
        return redisUserRepository.redisfindById(nom);
    }

    @GetMapping("/get/{nom}")
    public AppUser getbynom(@PathVariable("nom") final String nom) {

        return redisUserRepository.redisfindById(nom);
    }

    @GetMapping("/bool/{nom}")
    public boolean getbool (@PathVariable("nom") final String nom) {
        AppUser user=redisUserRepository.redisfindById(nom);
        if(user != null){
            return true;
        }


        return false;
    }


    @GetMapping("/delete/{nom}")
    public Map<String, AppUser> delete(@PathVariable("nom") final String nom) {
        redisUserRepository.redisdelete(nom);
        return all();
    }

    @GetMapping("/all")
    public Map<String, AppUser> all() {
        return redisUserRepository.redisfindAll();



    }
}