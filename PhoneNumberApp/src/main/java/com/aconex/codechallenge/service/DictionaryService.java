package com.aconex.codechallenge.service;

import java.util.List;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.pojos.DictionaryWord;

public interface DictionaryService {

    List<DictionaryWord> lookupWords(String string);

    void load(String string) throws AconexException;

}