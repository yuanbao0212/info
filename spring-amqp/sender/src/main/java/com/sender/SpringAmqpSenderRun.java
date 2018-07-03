package com.sender;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAmqpSenderRun {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringAmqpSenderRun.class, args);
		Sender sender = context.getBean(Sender.class);
		sender.send("hello world");
		
		context.close();
	}

	@Bean
	public CachingConnectionFactory myConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setHost("192.168.1.130");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate myExchangeTemplate(CachingConnectionFactory myConnectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(myConnectionFactory);
		rabbitTemplate.setExchange("test.topic");
		rabbitTemplate.setRoutingKey("test.abc.123");
		return rabbitTemplate;
	}
}
