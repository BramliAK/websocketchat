package com.chat.chatwebsocket.dao;

import com.chat.chatwebsocket.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Map;


public interface RedisUserRepository {
    void redissave(AppUser user);
    Map<String, AppUser> redisfindAll();
    AppUser redisfindById(String id);
    void redisupdate(AppUser user);
    void redisdelete(String id);
}
