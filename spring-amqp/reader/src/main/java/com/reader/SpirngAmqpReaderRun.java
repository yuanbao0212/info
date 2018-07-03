package com.reader;

import java.io.IOException;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpirngAmqpReaderRun {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpirngAmqpReaderRun.class, args);
		
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
	
	@Bean("myQueue2")
    Queue myQueue() {
        return QueueBuilder.durable("myQueue2").build();
    }
	
	@Bean
    Exchange myExchange() {
        return ExchangeBuilder.topicExchange("test.topic").durable(true).build();
    }

    @Bean
    public Binding myExchangeBinding(@Qualifier("myExchange") Exchange topicExchange,
                                     @Qualifier("myQueue2") Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with("test.#").noargs();
    }
}
