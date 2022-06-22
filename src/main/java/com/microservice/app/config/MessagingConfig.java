package com.microservice.app.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "user_service_queue";
    public static final String EXCHANGE = "user_service_exchange";
    public static final String ROUTING_KEY = "user_service_routing_key";

    //create queue
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    //create n exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    //bind queue and exchange
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(exchange()).with(ROUTING_KEY);
    }

    //message converter
    @Bean
    public MessageConverter messageConverter(){
        return (MessageConverter) new Jackson2JsonMessageConverter();
    }

    /**
     * Add the RabbitMq template
     */
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
        rabbitTemplate.setMessageConverter((org.springframework.amqp.support.converter.MessageConverter) messageConverter());
        return rabbitTemplate;
    }
}
