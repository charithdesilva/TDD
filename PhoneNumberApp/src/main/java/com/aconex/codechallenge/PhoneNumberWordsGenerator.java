/**
 * 
 */
package com.aconex.codechallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.aconex.codechallenge.exceptions.AconexException;

/**
 * @author cdesilva
 *
 */
public class PhoneNumberWordsGenerator {

    private static List<List<String>> phoneKeyMapper = null;

    public PhoneNumberWordsGenerator() {
	this.createPhoneNumberMapper();

    }

    /**
     * Generate list of possible words for input phone number.
     * 
     * @param phoneNumber
     * @return
     * @throws AconexException
     */
    public List<String> generateWords(String phoneNumber)
	    throws AconexException {
	List<String> words = new LinkedList<String>();
	List<String> wordsCache = new LinkedList<String>();

	String filteredPhoneNumber = phoneNumber.replaceAll("[^\\d]", "");
	try {
	    Integer.parseInt(filteredPhoneNumber);
	} catch (NumberFormatException e) {
	    throw new AconexException("Invalid phone number found : "
		    + phoneNumber);
	}

	this.generatePossibleWords("", filteredPhoneNumber, words, wordsCache);

	return words;
    }

    /**
     * Recursive method to scan all possible word combinations for given phone
     * number.
     * 
     * @param words
     * @param prefix
     * @param remainder
     * @param wordsCache
     *            purpose is to remove redundant scanning over same set of
     *            numbers. (improve performance immensely)
     */
    private void generatePossibleWords(String prefix, String remainder,
	    List<String> words, List<String> wordsCache) {

	int index = Integer.parseInt(remainder.substring(0, 1));

	for (int i = 0; i < phoneKeyMapper.get(index).size(); i++) {

	    String mappedChar = phoneKeyMapper.get(index).get(i);

	    if (prefix.equals("") && !wordsCache.isEmpty()) {

		for (String word : wordsCache) {
		    words.add(mappedChar + word);
		}

	    } else {

		if (remainder.length() == 1) {
		    String stringToBeAdded = prefix + mappedChar;
		    words.add(stringToBeAdded);
		    wordsCache.add(stringToBeAdded.substring(1));

		} else {
		    generatePossibleWords(prefix + mappedChar,
			    remainder.substring(1), words, wordsCache);
		}
	    }
	}
    }

    /**
     * initialize phone number to character 2D list
     */
    private void createPhoneNumberMapper() {

	if (phoneKeyMapper == null) {

	    phoneKeyMapper = new ArrayList<>();
	    phoneKeyMapper.add(0, new ArrayList<String>());
	    phoneKeyMapper.add(1, new ArrayList<String>());

	    phoneKeyMapper.add(2, new ArrayList<String>());
	    phoneKeyMapper.get(2).add("A");
	    phoneKeyMapper.get(2).add("B");
	    phoneKeyMapper.get(2).add("C");

	    phoneKeyMapper.add(3, new ArrayList<String>());
	    phoneKeyMapper.get(3).add("D");
	    phoneKeyMapper.get(3).add("E");
	    phoneKeyMapper.get(3).add("F");

	    phoneKeyMapper.add(4, new ArrayList<String>());
	    phoneKeyMapper.get(4).add("G");
	    phoneKeyMapper.get(4).add("H");
	    phoneKeyMapper.get(4).add("I");

	    phoneKeyMapper.add(5, new ArrayList<String>());
	    phoneKeyMapper.get(5).add("J");
	    phoneKeyMapper.get(5).add("K");
	    phoneKeyMapper.get(5).add("L");

	    phoneKeyMapper.add(6, new ArrayList<String>());
	    phoneKeyMapper.get(6).add("M");
	    phoneKeyMapper.get(6).add("N");
	    phoneKeyMapper.get(6).add("O");

	    phoneKeyMapper.add(7, new ArrayList<String>());
	    phoneKeyMapper.get(7).add("P");
	    phoneKeyMapper.get(7).add("Q");
	    phoneKeyMapper.get(7).add("R");
	    phoneKeyMapper.get(7).add("S");

	    phoneKeyMapper.add(8, new ArrayList<String>());
	    phoneKeyMapper.get(8).add("T");
	    phoneKeyMapper.get(8).add("U");
	    phoneKeyMapper.get(8).add("V");

	    phoneKeyMapper.add(9, new ArrayList<String>());
	    phoneKeyMapper.get(9).add("W");
	    phoneKeyMapper.get(9).add("X");
	    phoneKeyMapper.get(9).add("Y");
	    phoneKeyMapper.get(9).add("Z");
	}
    }

    public void generateWordsWithinWord(String phoneNumber,
	    String phoneNumberWord) {
	
	
	
	
	
    }

}
