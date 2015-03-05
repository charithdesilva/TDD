package com.aconex.codechallenge.service;

import java.util.List;
import java.util.Map;

import com.aconex.codechallenge.exceptions.AconexException;

public interface PhoneNumberToWordsService {

    /**
     * Process dictionary and prepare phoneNumberDictionaryWordMap
     * 
     * @param phoneNumberList
     * @param phoneNumberWordsGenerator
     * @return
     * @throws AconexException
     */
    Map<String, List<String>> buildWords(List<String> phoneNumberList)
	    throws AconexException;

    void setDictionaryService(DictionaryService dictionaryService);

}