package sss;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class sss {

    static int count = 1;
    public static void main(String[] args) {
	
	System.out.println("test");
	
	
	String word = "CHARITHDESILVA";
	
	Map<Integer, Set<String>> indexWordMap = new LinkedHashMap<>();
	
	for (int k = 1; k <= word.length(); k++) {
	    indexWordMap.put(k, new HashSet<String>());
	}

	int initialIndex = 1;
	extractWords(word, 0, word, word, initialIndex, indexWordMap, false, 0);
	
	System.out.println("loopcount "+count);
	
    }

    /**
     * @param word
     * @param indexPosition
     */
    private static void extractWords(String originalWord, int currentIndex, String parentWord, String word,  int indexPosition, Map<Integer, Set<String>> indexWordMap, boolean innerCall,int prefixIndex) {
	
	if (word.length() > indexPosition && indexPosition +2 <= word.length()) {
	    
	    String prefix = word.substring(0, indexPosition);
	    String suffix = word.substring(indexPosition, word.length());
	    prefixIndex = indexPosition;
	    
	    if(prefix.length() > 1) {
		System.out.println("word "+innerCall+" loop prefix "+currentIndex+" ----"+  indexPosition +" -- "+prefix);
		count += 1;
	    }
	    
	    currentIndex = currentIndex + indexPosition;
	    
	    if(suffix.length() > 1) {
		System.out.println("word "+innerCall+" loop  suffix "+currentIndex+" ----"+  indexPosition +" -- "+suffix);
		count += 1;
	    }
	    
	    System.out.println("++++++++++++++++++++++");
	    extractWords(originalWord, currentIndex , suffix, suffix, 2, indexWordMap, true, prefixIndex);
	    System.out.println("======================");
	    
	    if(!innerCall) {
		System.out.println("falsee");
	    }
	    
	    extractWords(originalWord, currentIndex, parentWord, suffix, indexPosition , indexWordMap, innerCall, prefixIndex);
	    
	    
	} else {
	    
	    if (indexPosition < parentWord.length()) {
		//currentIndex = currentIndex;
		if (!innerCall) {
		}
		System.out.println("??????????????????????????????? "+currentIndex);
		extractWords(originalWord, currentIndex , parentWord, parentWord, indexPosition + 1, indexWordMap, innerCall, prefixIndex);
	    }
	}
	
    }

}
