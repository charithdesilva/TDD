/**
 * 
 */
package com.aconex.codechallenge.components;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Charith De Silva
 *
 */
public class Dictionary {
    
    private static final Logger LOGGER = Logger.getLogger(Dictionary.class.getName());
    
    private static Dictionary dictionary = null;
    
    private Dictionary(){
    }
    
    public static Dictionary getInstance() {
	if (dictionary == null) {
	    dictionary = new Dictionary();
	}
	
	return dictionary;
    }

    private Map<String, String> dictionaryMap = new HashMap<>();

    public void addWord(String word) {
	dictionaryMap.put(word, word);
    }

    public String lookup(String wordStr) {
	return dictionaryMap.get(wordStr.toUpperCase());
    }

}
