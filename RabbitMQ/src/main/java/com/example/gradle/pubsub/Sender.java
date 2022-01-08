package com.example.gradle.pubsub;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Sender {
	@Autowired 
	private RabbitTemplate template; 
	
//	@Autowired 
//	private FanoutExchange fanout; 
	
	@Autowired 
	private DirectExchange direct; 
	
	AtomicInteger count = new AtomicInteger(0); 
	
	// 앱이 실행되는 동안 10초 마다 메시지를 보낸다. 
	@Scheduled(fixedDelay = 1000, initialDelay = 500) 
	public void send() throws JsonProcessingException { 
		
		// 메시지 모델 조립 
		EventPayload eventPayload = new EventPayload(); 
		eventPayload.setEventName("Hello " + count.incrementAndGet()); 
		Map<String, Object> eventData = new HashMap<>(); 
		eventData.put("value", 99999); 
		eventPayload.setData(eventData); 

		// 모든 Sender는 Queue에 직접 송신하지 않고, exchange를 지정하여 거기로 송신. 
		template.convertAndSend(RabbitMqConstants.EXCHANGE_NAME, RabbitMqConstants.ROUTING_KEY, eventPayload); 
		System.out.println("Publisher - Sent '" + new ObjectMapper().writeValueAsString(eventPayload) + "'"); 
	}
}
