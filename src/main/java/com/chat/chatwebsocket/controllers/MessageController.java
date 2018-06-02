package com.chat.chatwebsocket.controllers;

import com.chat.chatwebsocket.dao.MessageRepository;
import com.chat.chatwebsocket.model.Message;
import com.chat.chatwebsocket.model.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ahmed on 30/05/2018.
 */
@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/findmess")
    private List<Message> getmess(@Valid @RequestBody Send send){
        return messageRepository.findByFromuserAndTouserOrTouserAndFromuser(send.getFromuser(),send.getTouser(),send.getFromuser(),send.getTouser());
    }


}
