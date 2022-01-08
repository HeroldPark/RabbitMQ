package com.example.gradle.pubsub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//subscriber�� ������.
@Component
public class Receiver {
	// �� �κп� @RabbitListener�� �ٿ��� �ȴ�. (�̹� ���������� Config�� ���� �����ؼ� ó����.) 
	@RabbitListener(queues = RabbitMqConstants.QUEUE_NAME)
	public void receive(EventPayload eventPayload) throws JsonProcessingException { 
		ObjectMapper objectMapper = new ObjectMapper(); 
		System.out.println("\tSubscriber - Received '" + objectMapper.writeValueAsString(eventPayload) + "'"); 
		
		String payload = String.format("%s", objectMapper.writeValueAsString(eventPayload));
		EventPayload event = objectMapper.readValue(payload, EventPayload.class);
		System.out.printf("\tSubscriber - Received EventPayload = %s, %s\n", event.getData().get("value"), event.getEventName()); 
	}
}
