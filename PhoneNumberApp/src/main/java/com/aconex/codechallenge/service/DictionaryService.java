package com.aconex.codechallenge.service;

import com.aconex.codechallenge.exceptions.AconexException;

public interface DictionaryService {

    String lookupWord(String string) throws AconexException;

    void load(String string) throws AconexException;

}