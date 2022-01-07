package com.example.gradle.pubsub;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//subscriber�� ������.
@Component
public class Receiver {
	// �� �κп� @RabbitListener�� �ٿ��� �ȴ�. (�̹� ���������� Config�� ���� �����ؼ� ó����.) 
	@RabbitListener(queues = RabbitMqConstants.QUEUE_NAME)
	public void receive(EventPayload eventPayload /* Object payload */ ) throws JsonProcessingException { 
		ObjectMapper objectMapper = new ObjectMapper(); 
		System.out.println("\tSubscriber - Received '" + objectMapper.writeValueAsString(eventPayload) + "'"); 
		
//		System.out.println("\tSubscriber - Received '" + payload.toString()); 
//		
//		EventPayload eventPayload = objectMapper.readValue(payload.toString(), EventPayload.class);
//		System.out.printf("\tReceived %s\n", eventPayload.getData()); 
	}
}
