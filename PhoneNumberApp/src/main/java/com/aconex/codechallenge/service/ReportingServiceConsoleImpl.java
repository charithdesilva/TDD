/**
 * 
 */
package com.aconex.codechallenge.service;

import java.util.List;
import java.util.Map;

/**
 * @author cdesilva
 *
 */
public class ReportingServiceConsoleImpl implements ReportingService {
    
    /**
     * @param phoneNumberWordMap
     */
    @Override
    public void displayReport(Map<String, List<String>> phoneNumberWordMap) {
	if (!phoneNumberWordMap.isEmpty()) {

	    System.out
		    .println("================================================");

	    for (String key : phoneNumberWordMap.keySet()) {
		System.out
			.println("------------------------------------------------");
		System.out.println("Phone Number [" + key + "]");
		System.out
			.println("------------------------------------------------");
		int suggestion = 1;
		for (String word : phoneNumberWordMap.get(key)) {
		    System.out.println("           Suggestion ["
			    + suggestion + "] : " + word);
		    suggestion++;
		}
	    }
	    System.out
		    .println("================================================");
	    System.out.println();
	    System.out.println();
	} else {

	    System.out
		    .println("==================== System could not find any matchers ======================");
	}
    }

}
