/**
 * 
 */
package com.aconex.codechallenge.components;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cdesilva
 *
 */
public class Dictionary {
    
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
