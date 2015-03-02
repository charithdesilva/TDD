/**
 * 
 */
package com.aconex.codechallenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author chades
 *
 */
public class PhoneNumberWordsGeneratorTest {

    @Test
    public void givenPhoneNumberThenGenerateWordsList() {
	PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();
	Assert.assertTrue(phoneNumberWordsGenerator.generateWords("2255")
		.contains("CALL"));
    }
    
//    @Test
//    public void givenPhoneNumberThenGenerateWordsList() {
//	PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();
//	Assert.assertTrue(phoneNumberWordsGenerator.generateWords("2255")
//		.contains("CALL"));
//    }

}
