package com.epam.training.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training.springcore.interfaces.EventLogger;

public class App {

	Client client;
	EventLogger eventLogger;
	
	public App() {
		
	}
	
	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}
	
	public void logEvent(String msg) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		eventLogger.logEvent(message);
	}
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		App app = (App) ctx.getBean("app");
		
		app.logEvent("Some event for 1");
		app.logEvent("Some event for 2");
		
	}
}
