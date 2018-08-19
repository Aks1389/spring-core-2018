package com.epam.training.springcore;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training.springcore.enums.EventType;
import com.epam.training.springcore.interfaces.EventLogger;
import com.epam.training.springcore.loggers.CacheFileLogger;
import com.epam.training.springcore.loggers.FileEventLogger;

public class App {

	Client client;
	Event event;
	Map<EventType, EventLogger> loggers;
	CacheFileLogger defaultLogger;
	
	public App() {
		
	}
	
	public App(Client client, Event event, Map<EventType, EventLogger> loggers) {
		this.client = client;
		this.event = event;
		this.loggers = loggers;
	}
	
	public void logEvent(EventType type, String msg) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMsg(message);
		EventLogger logger = loggers.get(type);
		if(logger == null)
			logger = defaultLogger;
		logger.logEvent(event);
	}
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		App app = (App) ctx.getBean("app");
		/*
		for(int i = 0; i<6; i++)
			app.logEvent("Some event for "+ i);*/
		
		app.logEvent(EventType.INFO, "Some event for 2");
		app.logEvent(EventType.ERROR, "Some event for 2");
//		app.logEvent(EventType.DEFAULT, "Some event for 2");
		
		ctx.close();
	}
}