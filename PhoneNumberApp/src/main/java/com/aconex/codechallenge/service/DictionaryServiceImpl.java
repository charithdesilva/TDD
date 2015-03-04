/**
 * 
 */
package com.aconex.codechallenge.service;

import java.io.IOException;
import java.util.List;

import com.aconex.codechallenge.components.Dictionary;
import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.utils.FileUtility;

/**
 * @author cdesilva
 *
 */
public class DictionaryServiceImpl implements DictionaryService {

    private Dictionary dictionary = null;
    private String dictionaryDefaultFilePath = "English58k.txt";

    @Override
    public String lookupWord(String lookupString) throws AconexException {

	if (dictionary == null) {
	    this.loadDefaultDictionary(dictionaryDefaultFilePath);
	}
	
	return dictionary.lookup(lookupString);
    }

    @Override
    public void load(String file) throws AconexException {
	
	dictionary = Dictionary.getInstance();
	
	try {
	    FileUtility fileUtility = new FileUtility();
	    List<String> dictionaryWords = fileUtility.readFile(file);

	    loadWords(dictionaryWords);

	} catch (IOException e) {
	    throw new AconexException("Error reading file : " + file, e);
	}

    }

    /**
     * 
     * @param file
     * @throws AconexException
     */
    private void loadDefaultDictionary(String file) throws AconexException {
	
	dictionary = Dictionary.getInstance();
	
	try {
	    FileUtility fileUtility = new FileUtility();
	    List<String> dictionaryWords = fileUtility.readResourceFile(file);

	    loadWords(dictionaryWords);

	} catch (IOException e) {
	    throw new AconexException("Error reading file : " + file, e);
	}

    }
    
    /**
     * @param dictionaryWords
     */
    public void loadWords(List<String> dictionaryWords) {
	for (String dWord : dictionaryWords) {
	dictionary
		.addWord(dWord.replaceAll("[^\\w]", "").toUpperCase());
	}
    }

    @Override
    public Boolean isWordExists(String lookupString) {
	boolean exists = false;
	if(dictionary.lookup(lookupString) != null && !dictionary.lookup(lookupString).trim().equals("")){
	    exists = true;
	}
	return exists;
    }

}
