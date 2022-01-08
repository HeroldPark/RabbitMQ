package com.example.gradle.pubsub.component;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.gradle.pubsub.RabbitMqConstants;

/* * publisher, subscriber 모두에게 적용되는 내용. */ 
@Configuration
public class PubsubConfig {
	// exchange 정의. 
//	@Bean 
//	public FanoutExchange exchange() { 
//		return new FanoutExchange(RabbitMqConstants.EXCHANGE_NAME); 
//	} 
	
	@Bean 
	public DirectExchange exchange() { 
		return new DirectExchange(RabbitMqConstants.EXCHANGE_NAME); 
	} 
	
	@Bean 
	public Jackson2JsonMessageConverter jackson2MessageConverter() { 
		return new Jackson2JsonMessageConverter(); 
	}
}
