package com.epam.training.springcore.loggers;

import com.epam.training.springcore.Event;
import com.epam.training.springcore.interfaces.EventLogger;

public class ConsoleEventLogger implements EventLogger{

	public void logEvent(Event event) {
		System.out.println(event.toString());
	}
}
