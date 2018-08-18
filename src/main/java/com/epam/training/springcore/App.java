package com.epam.training.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training.springcore.interfaces.EventLogger;
import com.epam.training.springcore.loggers.CacheFileLogger;
import com.epam.training.springcore.loggers.FileEventLogger;

public class App {

	Client client;
	EventLogger eventLogger;
	Event event;
	CacheFileLogger cacheFileLogger;
	
	public App() {
		
	}
	
	public App(Client client, EventLogger eventLogger, Event event, CacheFileLogger cacheFileLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
		this.event = event;
		this.cacheFileLogger = cacheFileLogger;
	}
	
	public void logEvent(String msg) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMsg(message);
		eventLogger.logEvent(event);
		cacheFileLogger.logEvent(event);
	}
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		App app = (App) ctx.getBean("app");
		
		for(int i = 0; i<6; i++)
			app.logEvent("Some event for "+ i);
		
		ctx.close();
	}
}