package com.chat.chatwebsocket.dao;

import com.chat.chatwebsocket.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by ahmed on 27/05/2018.
 */
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<AppRole,Long> {
    public AppRole findByRole(String role);
}
