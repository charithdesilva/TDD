package sss;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class sss {

    static int count = 1;
    
    private String getFile(String fileName) {
	 
	StringBuilder result = new StringBuilder("");
 
	//Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource(fileName).getFile());
 
	try (Scanner scanner = new Scanner(file)) {
 
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}
 
		scanner.close();
 
	} catch (IOException e) {
		e.printStackTrace();
	}
 
	return result.toString();
 
  }
    public static void main(String[] args) {
	
	sss obj = new sss();
	System.out.println(obj.getFile("wordsEn.txt"));
//	
//	System.out.println("test");
//	String word = "ANDYFLLOWER";
//	
//	Map<Integer, Set<String>> indexWordMap = new LinkedHashMap<>();
//	
//	for (int k = 0; k < word.length(); k++) {
//	    indexWordMap.put(k, new HashSet<String>());
//	}
//
//	int initialIndex = 1;
//	//extractWords(word, 0, word, word, initialIndex, indexWordMap, false, 0);
//	wordExtractor(word,  1, 0, indexWordMap);
//	System.out.println("loopcount "+count);
	
    }

    
    private static void wordExtractor(String word, int indexPosition,
	    int parentWordIndex, Map<Integer, Set<String>> indexWordMap) {

	if (word.length() > indexPosition) {
	    String prefix = word.substring(0, indexPosition);
	    String suffix = word.substring(indexPosition, word.length());

	    if (prefix.length() > 1) {
		indexWordMap.get(parentWordIndex).add(prefix);
	    }

	    if (suffix.length() > 1) {
		indexWordMap.get((parentWordIndex + indexPosition)).add(suffix);
	    }

	    System.out.println("indexPosition >> " + indexPosition);
	    System.out.println("prefix " + parentWordIndex + ">> " + prefix);
	    System.out.println("suffix " + (parentWordIndex + indexPosition)
		    + ">> " + suffix);

	    wordExtractor(suffix, 2, parentWordIndex + indexPosition,
		    indexWordMap);
	    wordExtractor(word, indexPosition + 1, parentWordIndex,
		    indexWordMap);
	}

    }
    
    
    /**
     * @param word
     * @param indexPosition
     */
    private static void extractWords(String originalWord, int currentIndex, String parentWord, String word,  int indexPosition, Map<Integer, Set<String>> indexWordMap, boolean innerCall,int prefixIndex) {
	
	if (word.length() > indexPosition) {
	    
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
	    //extractWords(originalWord, currentIndex , suffix, suffix, 2, indexWordMap, true, prefixIndex);
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
		System.out.println("------------------------------ "+currentIndex);
		currentIndex = 0;
		System.out.println("??????????????????????????????? "+currentIndex);
		extractWords(originalWord, currentIndex , parentWord, parentWord, indexPosition + 1, indexWordMap, innerCall, prefixIndex);
	    }
	}
	
    }

}
