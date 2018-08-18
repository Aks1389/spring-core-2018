package com.epam.training.springcore;

import com.epam.training.springcore.interfaces.EventLogger;

public class ConsoleEventLogger implements EventLogger{

	public void logEvent(Event event) {
		System.out.println(event.toString());
	}
}
