package com.epam.training.springcore.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.epam.training.springcore.Event;
import com.epam.training.springcore.interfaces.EventLogger;

public class FileEventLogger implements EventLogger {

	private String fileName;
	protected File file;
	
	public FileEventLogger(String fileName) {
		this.fileName = fileName;
	}
	
	public void init() throws IOException {
		this.file = new File(fileName).getAbsoluteFile();
		file.createNewFile();
		if(!file.canWrite())
			throw new IOException("File is not writable");
	}
	
	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile (file, event.toString() + System.lineSeparator(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
