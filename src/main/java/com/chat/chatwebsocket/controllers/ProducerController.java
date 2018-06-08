package com.chat.chatwebsocket.controllers;


import com.chat.chatwebsocket.model.Message;
import com.chat.chatwebsocket.rabbitproducer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    Producer producer;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    String add(@RequestBody Message message) throws Exception {


        System.out.println(message.getMessage());
        producer.send(message);
        return "Received message: " + message.getFromuser() + "::" + message.getMessage();

    }
}
