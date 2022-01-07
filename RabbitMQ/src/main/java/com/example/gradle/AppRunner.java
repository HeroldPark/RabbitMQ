package com.example.gradle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//duration값 (default: 10 seconds) 만큼 앱을 구동시킴. 
@Profile("!test") // 테스트 버그 방지를 위함. 
@Component
public class AppRunner implements CommandLineRunner {
	@Value("${config.app.duration:0}") 
	private int duration; 
	
	@Autowired 
	private ConfigurableApplicationContext ctx; 
	
	@Override 
	public void run(String... arg0) throws Exception { 
		System.out.println("Ready ... running for " + duration + "ms"); 
		Thread.sleep(duration); 
		ctx.close(); 
	}
}
