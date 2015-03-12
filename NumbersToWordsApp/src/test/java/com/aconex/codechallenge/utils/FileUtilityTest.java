/**
 * 
 */
package com.aconex.codechallenge.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.codechallenge.PhoneNumberWordsAppTest;

/**
 * @author Charith De Silva
 *
 */
public class FileUtilityTest {

    @Test
    public void whenValidFileListThenReturnPhoneNumbersList()
	    throws IOException {
	FileUtility fileUtility = new FileUtility();
	List<String> filesList = new ArrayList<>();
	List<String> numbersList = null;
	filesList.add(PhoneNumberWordsAppTest.PHONE_LIST_PATH_1);
	numbersList = fileUtility.readFiles(filesList);
	Assert.assertEquals("333554", numbersList.get(0));
    }

    @Test(expected = IOException.class)
    public void whenInValidFileListThenReturnIOException() throws IOException {
	FileUtility fileUtility = new FileUtility();
	List<String> filesList = new ArrayList<>();
	filesList.add("fakeLocation/fakeFile.txt");
	fileUtility.readFiles(filesList);
	Assert.assertTrue(true);
    }

    @Test(expected = IOException.class)
    public void whenInValidResourceFileThenReturnIOException()
	    throws IOException {
	FileUtility fileUtility = new FileUtility();
	fileUtility.readResourceFile("fakeLocation/fakeFile.txt");
	Assert.assertTrue(true);
    }

    @Test
    public void whenValidResourceFileThenReturnList() throws IOException {
	FileUtility fileUtility = new FileUtility();
	fileUtility.readResourceFile("English58k.txt");
	Assert.assertTrue(true);
    }

}
