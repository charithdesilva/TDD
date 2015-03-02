/**
 * 
 */
package com.aconex.codechallenge.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aconex.codechallenge.FileUtility;
import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.pojos.Dictionary;
import com.aconex.codechallenge.pojos.DictionaryWord;

/**
 * @author cdesilva
 *
 */
public class DictionaryServiceImpl implements DictionaryService {

    private Dictionary dictionary = null;
    
    @Override
    public List<DictionaryWord> lookupWords(String lookupString) {
	
	List<DictionaryWord> words = new ArrayList<>();
	DictionaryWord dWord = new DictionaryWord();
	
	if() {
	    
	}
	
	dWord.setWord("CALL");
	dWord.setOriginalWord("CALL");
	words.add(dWord);
	
	return words;
    }

    @Override
    public void load(String file) throws AconexException {
	
	dictionary = new Dictionary();
	dictionary.setFilePath(file);
	
	try {
	    FileUtility fileUtility = new FileUtility();
	    List<String> dictionaryWords = fileUtility.readFile(file);
	    
	    for (String dictionaryWord : dictionaryWords) {
		dictionary.addWord();
	    }
	    
	} catch (IOException e) {
	    throw new AconexException("File reading error", e);
	}
	
    }

}
