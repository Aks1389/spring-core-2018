package com.epam.training.springcore.loggers;

import java.util.List;

import com.epam.training.springcore.Event;
import com.epam.training.springcore.interfaces.EventLogger;

public class CombinedEventLogger implements EventLogger {

	private List<EventLogger> loggers;
	
	public CombinedEventLogger(List<EventLogger> loggers) {
		this.loggers = loggers;
	}
	
	@Override
	public void logEvent(Event event) {
		loggers.forEach(logger -> logger.logEvent(event));
	}

}
