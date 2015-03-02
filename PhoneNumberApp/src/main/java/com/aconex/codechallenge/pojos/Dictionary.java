/**
 * 
 */
package com.aconex.codechallenge.pojos;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cdesilva
 *
 */
public class Dictionary {
    
    private Map<String, DictionaryWord> dictionaryMap = new HashMap<>();
    private String filePath = null;
    private StringBuffer dictionaryText = new StringBuffer();
    
    public Dictionary() {
	dictionaryText.append(",");
    }
    
    public void addWord(DictionaryWord word) {
	dictionaryMap.put(word.getWord(), word);
	dictionaryText.append(word.getWord()+",");
    }
    
    public void checkWord(String wordStr) {
	
	DictionaryWord word = dictionaryMap.get(wordStr.toUpperCase());
	
	Pattern p = Pattern.compile("^.*,("+word.getWord()+"\\w+),.*$");
	Matcher m = p.matcher(word.getWord());
	
        if(m.find()) {
            
            
            
            System.out.println("Matching group found 0: " +m.group(0));
            System.out.println("Matching group found 1: " +m.group(1));
            System.out.println("Matching group found 2: " +m.group(2));
        }
        
        
	word.setLastMatchedIndex();
    }
    
    
//    public Map<String, DictionaryWord> getDictionaryMap() {
//        return dictionaryMap;
//    }
//    
//    public void setDictionaryMap(Map<String, DictionaryWord> dictionaryMap) {
//        this.dictionaryMap = dictionaryMap;
//    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
}
