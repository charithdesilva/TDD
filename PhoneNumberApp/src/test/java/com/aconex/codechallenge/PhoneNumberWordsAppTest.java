package com.aconex.codechallenge;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.ExpectedException;

import com.aconex.codechallenge.exceptions.AconexException;

/**
 * @author chades
 *
 */
public class PhoneNumberWordsAppTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream
	    .emptyStandardInputStream();
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void whenPhoneNumberFilePathArgumentsPresent() {

	String args[] = { PHONE_LIST_PATH_1, PHONE_LIST_PATH_2 };
	try {
	    new PhoneNumberWordsApp().generateWords(args);
	} catch (AconexException e) {
	    // AconexException should not be thrown.
	    fail();
	}

	Assert.assertTrue(true);
    }

    @Test
    public void whenNoPhoneNumberFilePathArgumentsThenThrowExceptionWithMessage()
	    throws AconexException {
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Phone number list not provided.");
	new PhoneNumberWordsApp().generateWords(null);
    }

    @Test
    public void whenNoPhoneNumberFilePathArgumentsThenReceiveFromSystemIn() {
	systemInMock.provideText("31212\n313313\n");
	try {
	    new PhoneNumberWordsApp().generateWords(null);
	} catch (AconexException e) {
	    // AconexException should not be thrown.
	    fail();
	}
	Assert.assertTrue(true);
    }

    @Test
    public void whenWordDictionaryFilePathArgumentWithPhoneNumberFilePathArguments() {
	String args[] = { PHONE_LIST_PATH_1, PHONE_LIST_PATH_2, "-d",
		DICTIONARY_PATH };
	try {
	    new PhoneNumberWordsApp().generateWords(args);
	} catch (AconexException e) {
	    // AconexException should not be thrown.
	    fail();
	}
	Assert.assertTrue(true);
    }

    @Test
    public void whenWordDictionaryFilePathArgumentWithNoPhoneNumberFilePathArguments()
	    throws AconexException {
	String args[] = { "-d", DICTIONARY_PATH };
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Phone number list not provided.");
	new PhoneNumberWordsApp().generateWords(args);
	Assert.assertTrue(true);
    }

    @Test
    public void whenWordDictionaryFilePathArgumentWithPhoneNumberFilePathArgumentsReceiveFromSystemIn() {
	String args[] = { "-d", DICTIONARY_PATH };
	systemInMock.provideText("31212\n313313\n");
	try {
	    new PhoneNumberWordsApp().generateWords(args);
	} catch (AconexException e) {
	    // AconexException should not be thrown.
	    fail();
	}
	Assert.assertTrue(true);
    }

    @Test
    public void whenWordDictionaryFilePathArgumentSpecifiedButMissingThenThrowIlleagalArgument()
	    throws AconexException {
	String args[] = { PHONE_LIST_PATH_1, PHONE_LIST_PATH_2, "-d" };
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("Invalid dictionary path.");
	new PhoneNumberWordsApp().generateWords(args);

    }

    @Test
    public void whenPhoneNumberFilePathArgumentsInvalidThenFileNotFound()
	    throws AconexException {
	String args[] = { PHONE_LIST_PATH_1, "/fakefilelocation/file.txt" };
	expectedEx.expect(AconexException.class);
	expectedEx.expectMessage("File /fakefilelocation/file.txt not found.");
	new PhoneNumberWordsApp().generateWords(args);
	Assert.assertTrue(false);
    }
    
    @Test
    public void whenWordDictionaryFilePathArgumentWithPhoneNumberFilePathArgumentsReceiveFromSystemInForMultiWord() {
	String args[] = { "-d", DICTIONARY_PATH };
	systemInMock.provideText("225563\n225563669\n");
	try {
	    Map<String, List<String>> wordsMap = new PhoneNumberWordsApp().generateWords(args);
	    Assert.assertTrue(wordsMap.get("225563669").contains("CALL-ME-NOW"));
	} catch (AconexException e) {
	    // AconexException should not be thrown.
	    fail();
	}
	
    }    

    public static final String PHONE_LIST_PATH_1 = "src//test//resources//phonelistA.txt";
    public static final String PHONE_LIST_PATH_2 = "src//test//resources//phonelistB.txt";
    public static final String DICTIONARY_PATH = "src//test//resources//English58k.txt";

}
