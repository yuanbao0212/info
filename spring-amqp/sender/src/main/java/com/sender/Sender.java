package com.sender;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Resource
	RabbitTemplate rabbitTemplate;
	
	public void send(String message) {
		
		rabbitTemplate.convertAndSend(message);
		
	}
}
