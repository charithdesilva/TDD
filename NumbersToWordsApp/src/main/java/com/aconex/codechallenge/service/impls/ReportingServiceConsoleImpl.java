/**
 * 
 */
package com.aconex.codechallenge.service.impls;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.aconex.codechallenge.service.ReportingService;

/**
 * @author Charith De Silva
 *
 */
public class ReportingServiceConsoleImpl implements ReportingService {

    private static final Logger LOGGER = Logger
	    .getLogger(ReportingServiceConsoleImpl.class.getName());

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
		    System.out.println("           Suggestion [" + suggestion
			    + "] : " + word);
		    suggestion++;
		}
	    }
	    System.out
		    .println("================================================");
	    System.out.println();
	    System.out.println();
	} else {

	    System.out
		    .println("==================== No match found ======================");
	}
    }

}
