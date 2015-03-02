/**
 * 
 */
package com.aconex.codechallenge.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.codechallenge.PhoneNumberWordsAppTest;
import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.pojos.DictionaryWord;
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.service.DictionaryServiceImpl;

import static org.junit.Assert.fail;

/**
 * @author chades
 *
 */
public class DictionaryServiceTest {

    @Test
    public void givenValidParameterThenFindValueOnDefaultDictionary() {
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	List<DictionaryWord> matchingWords = dictionaryService.lookupWords("CALL");
	Assert.assertTrue(!matchingWords.isEmpty());
    }
    
    @Test
    public void whenDictionaryArgumentPresentThenLoadDictionary() {
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	try {
	    dictionaryService.load(PhoneNumberWordsAppTest.DICTIONARY_PATH);
	} catch (AconexException e) {
	    fail();
	}
	Assert.assertTrue(true);
    }
    
    
}
