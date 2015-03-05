/**
 * 
 */
package com.aconex.codechallenge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.PhoneNumberToWordsService;
import com.aconex.codechallenge.service.PhoneNumberToWordsServiceImpl;

/**
 * @author chades
 *
 */
public class PhoneNumberWordsGeneratorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void givenPhoneNumberThenGenerateWordsList() throws AconexException {
	PhoneNumberToWordsService phoneNumberWordsGenerator = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumberList = new ArrayList<>();
	phoneNumberList.add("225563");
	Assert.assertTrue(phoneNumberWordsGenerator.buildWords(phoneNumberList)
		.get("225563").contains("CALL-ME"));
    }

    @Test
    public void givenInvalidPhoneNumberThenThrowException()
	    throws AconexException {
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Invalid phone number found : INVALIDNUMBER");
	PhoneNumberToWordsService phoneNumberWordsGenerator = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumberList = new ArrayList<>();
	phoneNumberList.add("INVALIDNUMBER");
	phoneNumberWordsGenerator.buildWords(phoneNumberList);
	Assert.assertTrue(false);
    }

    @Test
    public void whenPhoneNumberWithSpecialCharactorsThenGenerateWordsList()
	    throws AconexException {
	PhoneNumberToWordsService phoneNumberWordsGenerator = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumberList = new ArrayList<>();
	phoneNumberList.add("@#2-2'5_5");
	Assert.assertTrue(phoneNumberWordsGenerator.buildWords(phoneNumberList)
		.get("@#2-2'5_5").contains("CALL"));
    }

    @Test
    public void whenNoPrimaryWordFoundForNumberThenGenerateCombinedWordsList()
	    throws AconexException {
	PhoneNumberToWordsService phoneNumberWordsGenerator = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumnersList = new ArrayList<>();
	phoneNumnersList.add("225563");
	Assert.assertTrue(phoneNumberWordsGenerator
		.buildWords(phoneNumnersList).get("225563").contains("CALL-ME"));
    }

}
