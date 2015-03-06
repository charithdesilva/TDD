/**
 * 
 */
package com.aconex.codechallenge;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.utils.LoggingHandler;

/**
 * @author Charith De Silva
 *
 */
public class PhoneWordsAppMain {

    private static final Logger LOGGER = Logger.getLogger(PhoneWordsAppMain.class.getName());
    
    /**
     * Application Entry point method.
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws AconexException {
	new LoggingHandler();
	LOGGER.info("------------------------------------------------");
	LOGGER.info("Phone-Words application starting ...");
	PhoneNumberWordsApp phoneNumberWordsApp = new PhoneNumberWordsApp();
	phoneNumberWordsApp.generateWords(args);
	LOGGER.info("Phone-Words application exiting ...");
	LOGGER.info("------------------------------------------------");
    }

}
