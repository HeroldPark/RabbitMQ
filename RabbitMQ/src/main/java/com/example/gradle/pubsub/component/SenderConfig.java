package com.example.gradle.pubsub.component;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.gradle.pubsub.Sender;

@Profile("sender") 
@Configuration
public class SenderConfig {
	@Bean 
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) { 
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); 
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter); // Json을 보내기 위해 메시지 컨버터 세팅. 
		return rabbitTemplate; 
	} 
	
	@Bean 
	public Sender sender() { 
		return new Sender(); 
	}
}
