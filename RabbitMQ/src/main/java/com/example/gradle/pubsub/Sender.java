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
	
	// ���� ����Ǵ� ���� 10�� ���� �޽����� ������. 
	@Scheduled(fixedDelay = 1000, initialDelay = 500) 
	public void send() throws JsonProcessingException { 
		
		// �޽��� �� ���� 
		EventPayload eventPayload = new EventPayload(); 
		eventPayload.setEventName("Hello " + count.incrementAndGet()); 
		Map<String, Object> eventData = new HashMap<>(); 
		eventData.put("value", 99999); 
		eventPayload.setData(eventData); 

		// ��� Sender�� Queue�� ���� �۽����� �ʰ�, exchange�� �����Ͽ� �ű�� �۽�. 
		template.convertAndSend(RabbitMqConstants.EXCHANGE_NAME, RabbitMqConstants.ROUTING_KEY, eventPayload); 
		System.out.println("Publisher - Sent '" + new ObjectMapper().writeValueAsString(eventPayload) + "'"); 
	}
}
