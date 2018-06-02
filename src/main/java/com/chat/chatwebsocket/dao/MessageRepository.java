package com.chat.chatwebsocket.dao;

import com.chat.chatwebsocket.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by ahmed on 30/05/2018.
 */
@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message,Long> {


    public List<Message> findByFromuserAndTouserOrTouserAndFromuser(String fromuser,String touser,String fromuser1,String touser1);
}
