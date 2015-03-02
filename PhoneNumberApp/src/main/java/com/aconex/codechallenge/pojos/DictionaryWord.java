/**
 * 
 */
package com.aconex.codechallenge.pojos;

/**
 * Dictionary Word Object
 * 
 * @author cdesilva
 *
 */
public class DictionaryWord {
    
    private String word =  null;
    private String originalWord = null;
    private int lastMatchedIndex = 0;
    
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getOriginalWord() {
        return originalWord;
    }
    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }
    public int getLastMatchedIndex() {
        return lastMatchedIndex;
    }
    public void setLastMatchedIndex(int lastMatchedIndex) {
        this.lastMatchedIndex = lastMatchedIndex;
    }

}
