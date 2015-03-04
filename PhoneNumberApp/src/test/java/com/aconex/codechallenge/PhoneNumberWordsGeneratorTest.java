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
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.service.DictionaryServiceImpl;

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
	    DictionaryService ds = new DictionaryServiceImpl();
	    ds.load(PhoneNumberWordsAppTest.DICTIONARY_PATH);
	    phoneNumberWordsGenerator.setDictionaryService(ds);
	    Assert.assertTrue(phoneNumberWordsGenerator.generateWords("225563")
	    	.contains("CALL-ME"));
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
//    
//    @Test
//    public void whenNoPrimaryWordFoundForNumberThenGenerateCombinedWordsList() {
//	try {
//	    PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();
//	    Assert.assertTrue(phoneNumberWordsGenerator.generateWordsWithinWord("225563").contains("ME"));
//	    	.contains("CALL"));
//	} catch (AconexException e) {
//	    fail();
//	}
//    }
    
    
//	StringBuffer s = new StringBuffer();
//	for (String e : words) {
//	    s.append(e + ",");
//	}
//	
//	try {
//	    
//
//		File file = new File("d:\\filename.txt");
//
//		// if file doesnt exists, then create it
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		bw.append(s);
//		bw.close();
//
//		System.out.println("Done");
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	}


}
