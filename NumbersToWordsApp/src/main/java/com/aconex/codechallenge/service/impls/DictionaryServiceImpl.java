/**
 * 
 */
package com.aconex.codechallenge.service.impls;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.aconex.codechallenge.components.Dictionary;
import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.utils.FileUtility;

/**
 * @author Charith De Silva
 *
 */
public class DictionaryServiceImpl implements DictionaryService {

    private static final Logger LOGGER = Logger
	    .getLogger(DictionaryServiceImpl.class.getName());

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

	    LOGGER.fine("dictionary loading with entries .. "
		    + dictionaryWords.size());
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

	    LOGGER.fine("dictionary loading with entries .. "
		    + dictionaryWords.size());
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
	    LOGGER.finest("Loading dictionary word >> "
		    + dWord.replaceAll("[^\\w]", "").toUpperCase());
	    dictionary.addWord(dWord.replaceAll("[^\\w]", "").toUpperCase());
	}
    }

    @Override
    public Boolean isWordExists(String lookupString) throws AconexException {
	boolean exists = false;

	if (dictionary == null) {
	    this.loadDefaultDictionary(dictionaryDefaultFilePath);
	}

	if (dictionary.lookup(lookupString) != null
		&& !dictionary.lookup(lookupString).trim().equals("")) {
	    exists = true;
	}
	return exists;
    }

}
