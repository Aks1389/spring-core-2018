package com.epam.training.springcore.loggers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.springcore.Event;

public class CacheFileLogger extends FileEventLogger{

	private int cacheSize;
	private List<Event> cache = new ArrayList<>();
	
	public CacheFileLogger(String fileName, int cacheSize) {
		super(fileName);
		this.cacheSize = cacheSize;
	}
	
	public void logEvent(Event event) {
		cache.add(event);
		
		if(cache.size() == cacheSize) {
			writeEventsFromCache();
			cache.clear();
		}
	}

	private void writeEventsFromCache() {
		try {
			FileWriter fWriter = new FileWriter(file, true);
			for (Event event : cache) {
				fWriter.write(event.toString() + System.lineSeparator());
			}
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		if(cache.size()>0) {
			writeEventsFromCache();
			cache.clear();
		}
	}
}
