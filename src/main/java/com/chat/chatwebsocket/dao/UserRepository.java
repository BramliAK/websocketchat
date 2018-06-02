package com.chat.chatwebsocket.dao;

import com.chat.chatwebsocket.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Map;

/**
 * Created by ahmed on 27/05/2018.
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<AppUser,Long> {


    public AppUser findBynom(String nom);


}
