package com.chat.chatwebsocket.rabbitproducer;


import com.chat.chatwebsocket.model.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitAdmin rabbitAdmin;


    public Queue declareUniqueQueue(String name) {
        Queue queue = new Queue(name);
        rabbitAdmin.declareQueue(queue);

        return queue;
    }

    public void send(Message message) throws Exception {


        Queue q=declareUniqueQueue(message.getFromuser()+"-"+message.getTouser());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(message.getFromuser()+"-"+message.getTouser(), message);
    }

}
