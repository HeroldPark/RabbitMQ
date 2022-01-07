package com.example.gradle.pubsub;

//상수 모음. 
public class RabbitMqConstants { 
	public final static String EXCHANGE_NAME = "sample.exchange"; 
	public final static String QUEUE_NAME = "sample.queue"; 
	public final static String ROUTING_KEY = "sample.key"; // FanoutExchange 를 쓰면 routing Key는 DEFAULT을 사용. 
}