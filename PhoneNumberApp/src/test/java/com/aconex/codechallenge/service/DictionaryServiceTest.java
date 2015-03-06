/**
 * 
 */
package com.aconex.codechallenge.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.codechallenge.PhoneNumberWordsAppTest;
import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.impls.DictionaryServiceImpl;

/**
 * @author Charith De Silva
 *
 */
public class DictionaryServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void givenValidParameterThenFindValueOnDefaultDictionary()
	    throws AconexException {
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	dictionaryService.load(PhoneNumberWordsAppTest.DICTIONARY_PATH);
	String matchingWord = dictionaryService.lookupWord("CALL");
	Assert.assertEquals("CALL", matchingWord);
    }

    @Test
    public void whenDictionaryDirectoryArgumentPresentThenLoadDictionary()
	    throws AconexException {
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	dictionaryService.load(PhoneNumberWordsAppTest.DICTIONARY_PATH);
	String matchingWord = dictionaryService.lookupWord("CALL");
	Assert.assertEquals("CALL", matchingWord);
    }

    @Test
    public void whenWrongDictionaryDirectoryArgumentPresentThenThrowException()
	    throws AconexException {
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Error reading file : fakedir/fakefile.txt");
	DictionaryService dictionaryService = new DictionaryServiceImpl();
	dictionaryService.load("fakedir/fakefile.txt");
	// should't reach this point
	Assert.assertTrue(false);
    }

}
