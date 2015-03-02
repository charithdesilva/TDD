/**
 * 
 */
package com.aconex.codechallenge.service;

import java.io.IOException;
import java.util.List;

import com.aconex.codechallenge.FileUtility;
import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.pojos.Dictionary;

/**
 * @author cdesilva
 *
 */
public class DictionaryServiceImpl implements DictionaryService {

    private Dictionary dictionary = null;
    private String dictionaryDefaultFilePath = "wordsEn.txt";

    @Override
    public String lookupWord(String lookupString) throws AconexException {
	
	if (dictionary == null) {
	    this.load(dictionaryDefaultFilePath);
	}
	
	return dictionary.lookup(lookupString);
    }

    @Override
    public void load(String file) throws AconexException {

	dictionary = new Dictionary();
	try {
	    FileUtility fileUtility = new FileUtility();
	    List<String> dictionaryWords = fileUtility.readFile(file);

	    for (String dWord : dictionaryWords) {
		dictionary.addWord(dWord.replaceAll("[^\\w]", "").toUpperCase());
	    }

	} catch (IOException e) {
	    throw new AconexException("Error reading file : "+file, e);
	}

    }

}
