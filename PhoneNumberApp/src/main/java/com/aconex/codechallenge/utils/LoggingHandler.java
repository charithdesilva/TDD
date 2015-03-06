package com.aconex.codechallenge.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingHandler {

    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger("confLogger");
    
    static {
	
	try {
		
	    // Get file from resources folder
	    ClassLoader classLoader = LoggingHandler.class.getClassLoader();
	    URL resource = classLoader.getResource("logging.properties");
	    File file = null;
	    if (resource != null) {
		file = new File(resource.getFile());
	    } else {
		    LOGGER.log(Level.SEVERE, "File error");
	    }
	    logManager.readConfiguration(new FileInputStream(file));
	} catch (IOException exception) {
	    LOGGER.log(Level.SEVERE, "Error in loading configuration",
		    exception);
	}
    }
    
}
