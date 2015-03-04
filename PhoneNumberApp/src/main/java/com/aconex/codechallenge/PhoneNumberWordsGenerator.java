/**
 * 
 */
package com.aconex.codechallenge;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.service.DictionaryServiceImpl;

/**
 * @author cdesilva
 *
 */
public class PhoneNumberWordsGenerator {

    private static List<List<String>> phoneKeyMapper = null;
    private DictionaryService dictionaryService = null;

    public PhoneNumberWordsGenerator() {
	if (phoneKeyMapper == null) {
	    this.createPhoneNumberMapper();
	}
	if(dictionaryService == null) {
	    dictionaryService = new DictionaryServiceImpl();
	}
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
	    BigInteger.valueOf(Long.parseLong(filteredPhoneNumber));
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

    /**
     * Extract all possible word combinations from a given word into HashMap
     * provided.
     * 
     * @param word
     * @param indexPosition
     * @param parentWordIndex
     * @param indexWordMap
     */
    public void extractWords(String word, int indexPosition,
	    int parentWordIndex) {

	if (word.length() > indexPosition) {
	    String prefix = word.substring(0, indexPosition);
	    String suffix = word.substring(indexPosition, word.length());

	    if (prefix.length() > 1 && this.dictionaryService.isWordExists(prefix)) {
		indexWordMap.get(parentWordIndex).add(prefix);
	    }

	    if (suffix.length() > 1 && this.dictionaryService.isWordExists(suffix)) {
		indexWordMap.get((parentWordIndex + indexPosition)).add(suffix);
	    }

	    extractWords(suffix, 2, parentWordIndex + indexPosition);
	    extractWords(word, indexPosition + 1, parentWordIndex);
	}

    }
    
    private Map<Integer, List<String>> indexWordMap = null;

    /**
     * Process dictionary and prepare phoneNumberDictionaryWordMap
     * 
     * @param phoneNumberList
     * @param phoneNumberWordsGenerator
     * @return
     * @throws AconexException
     */
    public Map<String, List<String>> buildWords(List<String> phoneNumberList)
	    throws AconexException {
	Map<String, List<String>> phoneNumberDictionaryWordMap = new LinkedHashMap<>();

	for (String phoneNumber : phoneNumberList) {

	    // find all matching name for given word
	    List<String> phoneNumberWords = this.generateWords(phoneNumber);
	    
	    List<String> dictionaryWords = new ArrayList<>();
	    for (String phoneNumberWord : phoneNumberWords) {

		String word = dictionaryService.lookupWord(phoneNumberWord);
		if (word != null) {
		    dictionaryWords.add(word);
		} else {
		    // find all possible combinations
		    dictionaryWords.addAll(this.generateWordsWithinWord(phoneNumber, phoneNumberWord));
		}

	    }
	    if (!dictionaryWords.isEmpty()) {
		phoneNumberDictionaryWordMap.put(phoneNumber, dictionaryWords);
	    }
	}
	return phoneNumberDictionaryWordMap;
    }

    /**
     * Handler method for extracting all words.
     * 
     * @param phoneNumberWord
     * @return 
     */
    public List<String> generateWordsWithinWord(String phoneNumber, String phoneNumberWord) {
	this.indexWordMap = new LinkedHashMap<>();
	List<Integer> numberList = new ArrayList<>();
	
	//initialize numbers list with associate index. 
	for (char ch : phoneNumber.toCharArray()) {
	    numberList.add(Integer.valueOf(ch));
	}

	//initialize map's inner Set objects. 
	for (int k = 0; k < phoneNumberWord.length(); k++) {
	    this.indexWordMap.put(k, new ArrayList<String>());
	}
	
	// recursive loop to find all possible combinations
	// TODO : refactoring opportunity where the 1st inner recursive call becomes too costly due to redundant searchers.  
	this.extractWords(phoneNumberWord, 1, 0);
	
	// this loop will concatenate possible word combinations together.  
	int index = 0;
	List<String> wordList = new ArrayList<String>();
	
	String prefix = "";
	int lastIndex = phoneNumberWord.length() + 1;
	
	generateMultiWordsForNumber(index, wordList, prefix,
		lastIndex);

	//remove out duplicate entries 
	if (!wordList.isEmpty()) {
	    Set<String> wordsSet = new HashSet<>();
	    wordsSet.addAll(wordList);
	    wordList.clear();
	    wordList.addAll(wordsSet);
	}
	
	return wordList;

	
    }

    /**
     * Generate string using multiple words to match phone number.  
     * 
     * @param indexWordMap
     * @param index
     * @param wordList
     * @param prefix
     * @param lastIndex
     */
    private void generateMultiWordsForNumber( int index,
	    List<String> wordList, String prefix, int lastIndex) {
	List<String> wordSet = this.indexWordMap.get(index);
	
	if (wordSet != null && !wordSet.isEmpty()) {
	    for(String word : wordSet) {
		String phoneWord = prefix + "-" + word;
		int lastWordIndex = index + word.length();
		
		if(lastIndex == (lastWordIndex + 1)) {
		    wordList.add(phoneWord.substring(1));
		} else {
		    generateMultiWordsForNumber(lastWordIndex, wordList, phoneWord, lastIndex);
		}
		
	    }
	}
    }

    /**
     * Makes up like of Indexes one can skip. 
     * @param word
     * @return
     */
    private List<Integer> extractSkipPositions(String word) {
	
	List<Integer> positions = new ArrayList<>();
	positions.add(0);

	for (int i = 0; i < word.length(); i++) {
	    if ((i + 2) <= word.length() && i > 2) {
		positions.add(i);
	    }
	}

	return positions;
    }  
    
    public void setDictionaryService(DictionaryService dictionaryService) {
	this.dictionaryService = dictionaryService;
    }

}
