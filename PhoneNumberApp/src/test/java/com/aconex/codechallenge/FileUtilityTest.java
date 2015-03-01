/**
 * 
 */
package com.aconex.codechallenge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author chades
 *
 */
public class FileUtilityTest {

	@Test
	public void whenValidFileListThenReturnPhoneList() {
		FileUtility fileUtility = new FileUtility();
		List<String> filesList = new ArrayList<>();
		List<String> numbersList =  fileUtility.readPhoneNumbers(filesList);
		Assert.assertEquals("1111", numbersList.get(0));
	}

}
