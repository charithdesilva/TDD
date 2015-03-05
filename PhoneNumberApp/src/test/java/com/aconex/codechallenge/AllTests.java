package com.aconex.codechallenge;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aconex.codechallenge.service.DictionaryServiceTest;
import com.aconex.codechallenge.utils.FileUtilityTest;

@RunWith(Suite.class)
@SuiteClasses({ PhoneNumberWordsAppTest.class,
	PhoneNumberWordsGeneratorTest.class, FileUtilityTest.class, DictionaryServiceTest.class })
public class AllTests {

}
