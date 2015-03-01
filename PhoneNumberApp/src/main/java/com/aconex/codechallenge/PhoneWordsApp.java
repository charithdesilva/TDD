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
public class PhoneWordsApp {

	/**
	 * Application Entry point method. 
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws AconexException {
		PhoneNumberWordsGenerator wordGenerator = new PhoneNumberWordsGenerator();
		wordGenerator.generateWords(args);
	}


}
