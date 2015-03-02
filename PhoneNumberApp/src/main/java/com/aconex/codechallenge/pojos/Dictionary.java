/**
 * 
 */
package com.aconex.codechallenge.pojos;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cdesilva
 *
 */
public class Dictionary {

    private Map<String, String> dictionaryMap = new HashMap<>();

    public void addWord(String word) {
	dictionaryMap.put(word, word);
    }

    public String lookup(String wordStr) {
	return dictionaryMap.get(wordStr.toUpperCase());
    }


}
