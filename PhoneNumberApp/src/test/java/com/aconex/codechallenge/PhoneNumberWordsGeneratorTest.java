/**
 * 
 */
package com.aconex.codechallenge;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.fail;

import com.aconex.codechallenge.exceptions.AconexException;

/**
 * @author chades
 *
 */
public class PhoneNumberWordsGeneratorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @Test
    public void givenPhoneNumberThenGenerateWordsList() {
	try {
	    PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();
	    Assert.assertTrue(phoneNumberWordsGenerator.generateWords("2255")
	    	.contains("CALL"));
	} catch (AconexException e) {
	    fail();
	}
    }
    
    @Test
    public void givenInvalidPhoneNumberThenThrowException() throws AconexException {
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Invalid phone number found : INVALIDNUMBER");
	PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();
	phoneNumberWordsGenerator.generateWords("INVALIDNUMBER");
	Assert.assertTrue(false);
    }
    
    @Test
    public void whenPhoneNumberWithSpecialCharactorsThenGenerateWordsList() {
	try {
	    PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();
	    Assert.assertTrue(phoneNumberWordsGenerator.generateWords("@#2-2'5_5")
	    	.contains("CALL"));
	} catch (AconexException e) {
	    fail();
	}
    }

}
