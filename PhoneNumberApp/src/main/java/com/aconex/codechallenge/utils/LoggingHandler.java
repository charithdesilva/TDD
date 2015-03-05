package com.aconex.codechallenge.utils;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingHandler {
    
    private static LoggingHandler handler = null;
    private static final Logger LOGGER = Logger.getLogger(LoggingHandler.class
	    .getName());

    private LoggingHandler() {

    }
    
    public static LoggingHandler getInstance() {
	if(handler == null) {
	    handler = new LoggingHandler();
	}
	
	return handler;
    }

    public void configure() {

	Handler consoleHandler = null;
	Handler fileHandler = null;

	try {
	    consoleHandler = new ConsoleHandler();

	    fileHandler = new FileHandler("./aconex_debug.log");

	    // Assigning handlers to LOGGER object
	    LOGGER.addHandler(consoleHandler);
	    LOGGER.addHandler(fileHandler);

	    // Setting levels to handlers and LOGGER
	    consoleHandler.setLevel(Level.INFO);
	    fileHandler.setLevel(Level.FINE);
	    LOGGER.setLevel(Level.ALL);

	    // Console handler removed
	    LOGGER.removeHandler(consoleHandler);

	} catch (IOException exception) {
	    LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
	}

    }

}
