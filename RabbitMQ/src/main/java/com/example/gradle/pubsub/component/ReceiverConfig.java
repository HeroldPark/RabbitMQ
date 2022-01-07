package com.example.gradle.pubsub.component;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.gradle.pubsub.RabbitMqConstants;
import com.example.gradle.pubsub.Receiver;

//subscriber�� exchange, queue �׸��� ���� �����ϴ� binding�� ��� �����Ѵ�. 
@Profile("receiver") 
@Configuration
public class ReceiverConfig {
	// durable, non-exclusive, non-autoDelete queue 1�� ����. 
	@Bean 
	public Queue queue() { 
		return new Queue(RabbitMqConstants.QUEUE_NAME); 
	} 
	
	// exchange�� queue�� binding ���ش�. ��, exchange���� �����͸� ������ Queue ���Ǵ� Receiver�� å���̴�. 
	@Bean 
	public Binding binding(FanoutExchange exchange, Queue tutorialQueue1) { 
		return BindingBuilder.bind(tutorialQueue1).to(exchange); 
	} 
	
	// ���ǵ� �޽��� �����ʸ� ����. 
	@Bean 
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) { 
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(); 
		container.setConnectionFactory(connectionFactory); 
		container.setQueueNames(RabbitMqConstants.QUEUE_NAME); 
		container.setMessageListener(listenerAdapter); return container; } 
	
	// �޽��� �����ʸ� �����Ѵ�. Subscriber�� ���� ���� ��. 
	@Bean 
	MessageListenerAdapter listenerAdapter(Receiver receiver, Jackson2JsonMessageConverter jackson2JsonMessageConverter) { 
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver, "receive"); 
		messageListenerAdapter.setDefaultListenerMethod("receive"); // ������ �޼ҵ� ����. 
		messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter); // Json���·� �ޱ� ���� MessageConverter ����. 
		return messageListenerAdapter; 
	} 
	
	@Bean 
	public Receiver receiver() { 
		return new Receiver(); 
	}
}
