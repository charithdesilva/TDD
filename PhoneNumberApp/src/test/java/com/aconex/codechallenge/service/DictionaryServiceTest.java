/**
 * 
 */
package com.aconex.codechallenge.service;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.codechallenge.PhoneNumberWordsAppTest;
import com.aconex.codechallenge.exceptions.AconexException;

/**
 * @author chades
 *
 */
public class DictionaryServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void givenValidParameterThenFindValueOnDefaultDictionary() {
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	try {
	    dictionaryService.load(PhoneNumberWordsAppTest.DICTIONARY_PATH);
	    String matchingWord = dictionaryService
		    .lookupWord("CALL");
	    Assert.assertEquals("CALL", matchingWord);
	} catch (AconexException e) {
	    fail();
	}
    }

    @Test
    public void whenDictionaryDirectoryArgumentPresentThenLoadDictionary() {
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	try {
	    dictionaryService.load(PhoneNumberWordsAppTest.DICTIONARY_PATH);
	    String matchingWord = dictionaryService
		    .lookupWord("CALL");
	    Assert.assertEquals("CALL", matchingWord);
	} catch (AconexException e) {
	    fail();
	}
    }

    @Test
    public void whenWrongDictionaryDirectoryArgumentPresentThenThrowException()
	    throws AconexException {
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Error reading file : fakedir/fakefile.txt");
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	dictionaryService.load("fakedir/fakefile.txt");
	// can't reach this point
	Assert.assertTrue(false);
    }

}