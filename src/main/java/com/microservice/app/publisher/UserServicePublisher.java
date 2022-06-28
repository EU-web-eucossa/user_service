package com.microservice.app.publisher;

import com.microservice.app.dto.UserDto;
import com.microservice.app.model.User;
import com.microservice.app.service.UserNotFoundException;
import com.microservice.app.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserServicePublisher {
    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate template;

//    @Scheduled(fixedRate = 10000)
    public String publishUserService() throws UserNotFoundException {
        String user = "user object";

        Optional<User> user1 = userService.getUserByEmailAddress("oduorfrancis134@gmail.com");
        System.out.println("sending user object...");
        template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,user1);
        return "send successful";
    }
}
