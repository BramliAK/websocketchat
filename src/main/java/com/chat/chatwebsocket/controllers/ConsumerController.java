package com.chat.chatwebsocket.controllers;

//import org.springframework.amqp.core.Message;


import com.chat.chatwebsocket.model.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    RabbitTemplate template;

    @GetMapping("/{fromuser}/{touser}")
    public Message ReceiveMessage(@PathVariable("fromuser") final String fromuser, @PathVariable("touser") final String touser){
       // Message received =template.receive("mmm-dddd");
        Message received = (Message) template.receiveAndConvert(fromuser+"-"+touser);
        System.out.println("llllll "+ received.getMessage());
        return  received;
    }
}
