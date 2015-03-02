/**
 * 
 */
package com.aconex.codechallenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author chades
 *
 */
public class FileUtilityTest {

    @Test
    public void whenValidFileListThenReturnPhoneNumbersList() {

	FileUtility fileUtility = new FileUtility();
	List<String> filesList = new ArrayList<>();
	List<String> numbersList = null;

	filesList.add(PhoneNumberWordsAppTest.PHONE_LIST_PATH_1);

	try {
	    numbersList = fileUtility.readFiles(filesList);
	} catch (IOException e) {
	    fail();
	}
	Assert.assertEquals("121212", numbersList.get(0));
    }
    
    @Test(expected = IOException.class)
    public void whenInValidFileListThenReturnIOException() throws IOException {
	
	FileUtility fileUtility = new FileUtility();
	List<String> filesList = new ArrayList<>();
	filesList.add("fakeLocation/fakeFile.txt");
	fileUtility.readFiles(filesList);
	Assert.assertTrue(true);
    }
    
//    @Test
//    public void whenInValidFileListThenReturnIOException() throws IOException {
//	
//	FileUtility fileUtility = new FileUtility();
//	List<String> filesList = new ArrayList<>();
//	filesList.add("fakeLocation/fakeFile.txt");
//	fileUtility.readFiles(filesList);
//	Assert.assertTrue(true);
//    }
    
    

}
