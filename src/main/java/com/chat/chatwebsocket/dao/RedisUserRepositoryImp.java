package com.chat.chatwebsocket.dao;

import com.chat.chatwebsocket.model.AppUser;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class RedisUserRepositoryImp implements RedisUserRepository {

    private RedisTemplate<String, AppUser> redisTemplate;

    private HashOperations hashOperations;


    public RedisUserRepositoryImp(RedisTemplate<String, AppUser> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void redissave(AppUser user) {
        hashOperations.put("USER", user.getNom() , user);

    }

    @Override
    public Map<String, AppUser> redisfindAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public AppUser redisfindById(String id) {
        return (AppUser)hashOperations.get("USER", id);
    }

    @Override
    public void redisupdate(AppUser user) {
        redissave(user);
    }

    @Override
    public void redisdelete(String id) {
        hashOperations.delete("USER", id);
    }
}
