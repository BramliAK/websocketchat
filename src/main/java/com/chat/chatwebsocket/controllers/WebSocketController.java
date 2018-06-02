package com.chat.chatwebsocket.controllers;

import com.chat.chatwebsocket.dao.MessageRepository;
import com.chat.chatwebsocket.dao.RedisUserRepository;
import com.chat.chatwebsocket.dao.UserRepository;
import com.chat.chatwebsocket.model.AppUser;
import com.chat.chatwebsocket.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by ahmed on 28/05/2018.
 */
@RestController
public class WebSocketController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUserRepository redisUserRepository;

    private  final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;

    }
    @MessageMapping("/hello")
    public Message onRecive(Message message){
        System.out.println(message.getFromuser()+" "+message.getTouser());

        this.template.convertAndSend("/topic/"+message.getFromuser(),message);
        this.template.convertAndSend("/topic/"+message.getTouser(),message);
        messageRepository.save(message);
        return message;
    }

    @MessageMapping("/connecte")
    public AppUser connecte(String user){
        System.out.println(user);
        AppUser u=userRepository.findBynom(user);
        this.template.convertAndSend("/topic/connecte/user",u);
        redisUserRepository.redissave(u);


        return u;
    }

    @MessageMapping("/deconnecte")
    public AppUser deconnecte(String user){
        System.out.println("****************");
        redisUserRepository.redisdelete(user);
        AppUser u=userRepository.findBynom(user);
        redisUserRepository.redisfindAll();
        this.template.convertAndSend("/topic/deconnecte/user",redisUserRepository.redisfindAll()    );




        return u;
    }
}
