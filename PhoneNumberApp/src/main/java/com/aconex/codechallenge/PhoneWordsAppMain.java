/**
 * 
 */
package com.aconex.codechallenge;

import java.io.FileNotFoundException;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.utils.LoggingHandler;

/**
 * @author Charith De Silva
 *
 */
public class PhoneWordsAppMain {

    /**
     * Application Entry point method.
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws AconexException {
	LoggingHandler loggerHandler = LoggingHandler.getInstance();
	loggerHandler.configure();
	PhoneNumberWordsApp phoneNumberWordsApp = new PhoneNumberWordsApp();
	phoneNumberWordsApp.generateWords(args);
    }

}
