package com.aconex.codechallenge.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingHandler {

    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger("confLogger");
    
    static {
	
	try {
	    // Get file from resources folder
	    FileUtility util = new FileUtility();
	    logManager.readConfiguration(util.getFileInputStream("logging.properties"));
	} catch (IOException exception) {
	    LOGGER.log(Level.SEVERE, "Error in loading configuration",
		    exception);
	}
    }
    
}
