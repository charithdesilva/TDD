/**
 * 
 */
package com.aconex.codechallenge;

import java.io.FileNotFoundException;

import com.aconex.codechallenge.exceptions.AconexException;

/**
 * @author chades
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
	PhoneNumberWordsApp phoneNumberWordsApp = new PhoneNumberWordsApp();
	phoneNumberWordsApp.generateWords(args);
    }

}
