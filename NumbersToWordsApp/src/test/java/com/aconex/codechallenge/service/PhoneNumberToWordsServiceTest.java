/**
 * 
 */
package com.aconex.codechallenge.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.impls.PhoneNumberToWordsServiceImpl;

/**
 * @author Charith De Silva
 *
 */
public class PhoneNumberToWordsServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void givenPhoneNumberThenGenerateWordsList() throws AconexException {
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumberList = new ArrayList<>();
	phoneNumberList.add("225563");
	Assert.assertTrue(phoneNumberToWordsService.buildWords(phoneNumberList)
		.get("225563").contains("CALL-ME"));
    }

    @Test
    public void givenInvalidPhoneNumberThenThrowException()
	    throws AconexException {
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Invalid phone number found : INVALIDNUMBER");
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumberList = new ArrayList<>();
	phoneNumberList.add("INVALIDNUMBER");
	phoneNumberToWordsService.buildWords(phoneNumberList);
	Assert.assertTrue(false);
    }

    @Test
    public void whenPhoneNumberWithSpecialCharactorsThenGenerateWordsList()
	    throws AconexException {
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumberList = new ArrayList<>();
	phoneNumberList.add("@#2-2'5_5");
	Assert.assertTrue(phoneNumberToWordsService.buildWords(phoneNumberList)
		.get("@#2-2'5_5").contains("CALL"));
    }

    @Test
    public void whenNoPrimaryWordFoundForNumberThenGenerateCombinedWordsList()
	    throws AconexException {
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumnersList = new ArrayList<>();
	phoneNumnersList.add("225563");
	Assert.assertTrue(phoneNumberToWordsService
		.buildWords(phoneNumnersList).get("225563").contains("CALL-ME"));
    }
    
    @Test
    public void whenNumberHas1or0ThenThrowException()
	    throws AconexException {
	
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Invalid phone number found : 1225563");
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumnersList = new ArrayList<>();
	phoneNumnersList.add("1225563");
	Assert.assertTrue(phoneNumberToWordsService
		.buildWords(phoneNumnersList).get("1225563").contains("1CALL-ME"));
    }
    
    @Test
    public void whenNoResultsFoundThenTryDigitsLeftOff()
	    throws AconexException {
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumnersList = new ArrayList<>();
	phoneNumnersList.add("922559639");
	Assert.assertTrue(phoneNumberToWordsService
		.buildWords(phoneNumnersList).get("922559639").contains("9CALL-9ME9"));
    }
    
    
    @Test
    public void whenNoResultsFoundEvenWithDigitCombinationsThenReturnEmptyListThen()
	    throws AconexException {
	PhoneNumberToWordsService phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	List<String> phoneNumnersList = new ArrayList<>();
	phoneNumnersList.add("9999999");
	Assert.assertTrue(phoneNumberToWordsService
		.buildWords(phoneNumnersList).isEmpty());
    }
    

}
