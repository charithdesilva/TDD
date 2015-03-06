/**
 * 
 */
package com.aconex.codechallenge.service.impls;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.service.PhoneNumberToWordsService;

/**
 * @author Charith De Silva
 *
 */
public class PhoneNumberToWordsServiceImpl implements PhoneNumberToWordsService {
    
    private static final Logger LOGGER = Logger.getLogger(PhoneNumberToWordsServiceImpl.class.getName());

    private static List<List<String>> phoneKeyMapper = null;
    private DictionaryService dictionaryService = null;

    /**
     * public constructor
     */
    public PhoneNumberToWordsServiceImpl() {
	if (phoneKeyMapper == null) {
	    this.createPhoneNumberMapper();
	}
	if (dictionaryService == null) {
	    dictionaryService = new DictionaryServiceImpl();
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
     * Process dictionary and prepare phoneNumberDictionaryWordMap
     * 
     * @param phoneNumberList
     * @param phoneNumberWordsGenerator
     * @return
     * @throws AconexException
     */
    @Override
    public Map<String, List<String>> buildWords(List<String> phoneNumberList)
	    throws AconexException {

	Map<String, List<String>> phoneNumberDictionaryWordMap = new LinkedHashMap<>();

	for (String phoneNumber : phoneNumberList) {

	    String filteredNumber = phoneNumber.replaceAll("[^\\d]", "");

	    if (filteredNumber == null || filteredNumber.trim().equals("")) {
		LOGGER.severe("Invalid phone number found : "+ phoneNumber);
		throw new AconexException("Invalid phone number found : "
			+ phoneNumber);
	    }

	    // find all matching name for given word
	    List<String> dictionaryWords = filterValidWords(filteredNumber,
		    this.generateWords(filteredNumber));

	    // if empty try with numeric digit in
	    if (dictionaryWords.isEmpty()) {

		List<Integer> ignoreIdexes = new ArrayList<>();
		ignoreIdexes.add(1);
		ignoreIdexes.add((filteredNumber.length() - 2));
		
		List<String> prefixWordsList = new ArrayList<>();
		List<String> suffixWordsList = new ArrayList<>();
		
		for (int i = 0; i < filteredNumber.length(); i++) {
		    if (!ignoreIdexes.contains(i)) {
			
			String prefixNumber = filteredNumber.substring(0,i);
			String suffixNumber = filteredNumber.substring(i+1, filteredNumber.length());
			
			if(i == 0) {
			    prefixWordsList.add("");
			} else if (null != prefixNumber && !prefixNumber.trim().equals("")) {
			    prefixWordsList = filterValidWords(prefixNumber, this.generateWords(prefixNumber));
			}
			
			if (null != suffixNumber && !suffixNumber.trim().equals("")) {
			    suffixWordsList = filterValidWords(prefixNumber, this.generateWords(suffixNumber));
			}
			
			if(prefixWordsList.isEmpty() && suffixWordsList.isEmpty())
			    continue;
			
			for(String prefixWord : prefixWordsList) {
			    for(String suffixWord : suffixWordsList) {
				dictionaryWords.add(prefixWord + filteredNumber.toCharArray()[i] + suffixWord);
				LOGGER.fine("ADDING : " +prefixNumber + "["+ filteredNumber.toCharArray()[i] + "]"+ suffixNumber); 
			    }
			}
			
		    }
		}
	    }

	    if (!dictionaryWords.isEmpty()) {
		phoneNumberDictionaryWordMap.put(phoneNumber, dictionaryWords);
	    }
	}

	return phoneNumberDictionaryWordMap;
    }

    /**
     * @param filteredNumber
     * @param phoneNumberWords
     * @return
     * @throws AconexException
     */
    private List<String> filterValidWords(String filteredNumber,
	    List<String> phoneNumberWords) throws AconexException {
	List<String> dictionaryWords = new ArrayList<>();

	for (String phoneNumberWord : phoneNumberWords) {

	    // capture matching name for full phone number
	    String word = dictionaryService.lookupWord(phoneNumberWord);
	    if (word != null) {
		dictionaryWords.add(word);
	    }

	    // find all other possible combinations
	    dictionaryWords.addAll(this.generateWordsWithinWord(filteredNumber,
		    phoneNumberWord));

	}
	return dictionaryWords;
    }

    /**
     * Generate list of possible words for input phone number.
     * 
     * @param phoneNumber
     * @return
     * @throws AconexException
     */
    private List<String> generateWords(String phoneNumber)
	    throws AconexException {
	List<String> words = new LinkedList<String>();
	List<String> wordsCache = new LinkedList<String>();

	try {
	    BigInteger.valueOf(Long.parseLong(phoneNumber));
	} catch (NumberFormatException e) {
	    LOGGER.severe("Invalid phone number found : "+ phoneNumber);
	    throw new AconexException("Invalid phone number found : "
		    + phoneNumber);
	}

	this.generatePossibleWords("", phoneNumber, words, wordsCache);

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

	LOGGER.fine("generatePossibleWords = prefix"+ prefix +" remainder "+remainder);
	
	int index = Integer.parseInt(remainder.substring(0, 1));
	
	LOGGER.fine("index "+ index);

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
		    LOGGER.finest("adding stringToBeAdded = "+ stringToBeAdded.substring(1));

		} else {
		    generatePossibleWords(prefix + mappedChar,
			    remainder.substring(1), words, wordsCache);
		}
	    }
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
     * @throws AconexException
     */
    private void extractWords(String word, int indexPosition,
	    int parentWordIndex) throws AconexException {

	if (word.length() > indexPosition) {
	    String prefix = word.substring(0, indexPosition);
	    String suffix = word.substring(indexPosition, word.length());

	    if (prefix.length() > 1
		    && this.dictionaryService.isWordExists(prefix)) {
		LOGGER.finest("adding indexWordMap = ["+ parentWordIndex +"]"+ prefix);
		indexWordMap.get(parentWordIndex).add(prefix);
	    }

	    if (suffix.length() > 1
		    && this.dictionaryService.isWordExists(suffix)) {
		indexWordMap.get((parentWordIndex + indexPosition)).add(suffix);
		LOGGER.finest("adding indexWordMap = ["+ (parentWordIndex + indexPosition) +"]"+ suffix);
	    }

	    extractWords(suffix, 2, parentWordIndex + indexPosition);
	    extractWords(word, indexPosition + 1, parentWordIndex);
	}

    }

    private Map<Integer, List<String>> indexWordMap = null;

    /**
     * Handler method for extracting all words.
     * 
     * @param phoneNumberWord
     * @return
     * @throws AconexException
     */
    private List<String> generateWordsWithinWord(String phoneNumber,
	    String phoneNumberWord) throws AconexException {
	this.indexWordMap = new LinkedHashMap<>();
	List<Integer> numberList = new ArrayList<>();

	// initialize numbers list with associate index.
	for (char ch : phoneNumber.toCharArray()) {
	    numberList.add(Integer.valueOf(ch));
	}

	// initialize map's inner Set objects.
	for (int k = 0; k < phoneNumberWord.length(); k++) {
	    this.indexWordMap.put(k, new ArrayList<String>());
	}

	// recursive loop to find all possible combinations
	// TODO : refactoring opportunity where the 1st inner recursive call
	// becomes too costly due to redundant searchers.
	this.extractWords(phoneNumberWord, 1, 0);
	// this loop will concatenate possible word combinations together.
	int index = 0;
	List<String> wordList = new ArrayList<String>();

	String prefix = "";
	int lastIndex = phoneNumberWord.length() + 1;

	generateMultiWordsForNumber(index, wordList, prefix, lastIndex);

	// remove out duplicate entries
	if (!wordList.isEmpty()) {
	    Set<String> wordsSet = new HashSet<>();
	    wordsSet.addAll(wordList);
	    wordList.clear();
	    wordList.addAll(wordsSet);
	}

	LOGGER.finest("extracted words "+phoneNumberWord+" >> "+wordList.size());
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
    private void generateMultiWordsForNumber(int index, List<String> wordList,
	    String prefix, int lastIndex) {
	List<String> wordSet = this.indexWordMap.get(index);

	if (wordSet != null && !wordSet.isEmpty()) {
	    for (String word : wordSet) {
		String phoneWord = prefix + "-" + word;
		int lastWordIndex = index + word.length();

		if (lastIndex == (lastWordIndex + 1)) {
		    wordList.add(phoneWord.substring(1));
		} else {
		    generateMultiWordsForNumber(lastWordIndex, wordList,
			    phoneWord, lastIndex);
		}

	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aconex.codechallenge.service.PhoneNumberToWordsService#
     * setDictionaryService(com.aconex.codechallenge.service.DictionaryService)
     */
    @Override
    public void setDictionaryService(DictionaryService dictionaryService) {
	this.dictionaryService = dictionaryService;
    }

}
