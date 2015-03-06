package com.aconex.codechallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.service.DictionaryServiceImpl;
import com.aconex.codechallenge.service.PhoneNumberToWordsService;
import com.aconex.codechallenge.service.PhoneNumberToWordsServiceImpl;
import com.aconex.codechallenge.service.ReportingService;
import com.aconex.codechallenge.service.ReportingServiceConsoleImpl;
import com.aconex.codechallenge.utils.FileUtility;

/**
 * @author Charith De Silva
 *
 */
public class PhoneNumberWordsApp {
    
    private static final Logger LOGGER = Logger.getLogger(PhoneNumberWordsApp.class.getName());


    private String dictionaryFilePath = null;
    private DictionaryService dictionaryService = null;
    private PhoneNumberToWordsService phoneNumberToWordsService = null;
    private ReportingService ReportingService = null;
    
    /**
     * Entry point for word-generator app.
     * 
     * @param parameters
     * @return
     * @throws AconexException
     */
    public Map<String, List<String>> generateWords(String[] parameters)
	    throws AconexException {
	
	LOGGER.info("Phone word finder started ...");
	LOGGER.fine("this is finer");
	
	List<String> filesList = new ArrayList<>();
	List<String> phoneNumberList = new ArrayList<>();
	FileUtility fileUtility = new FileUtility();
	Map<String, List<String>> phoneNumberWordMap = null;
	
	phoneNumberToWordsService = new PhoneNumberToWordsServiceImpl();
	ReportingService = new ReportingServiceConsoleImpl();

	int count = 0;

	try {

	    processArgumentsInput(parameters, filesList, count);
	    processSystemInput(filesList, phoneNumberList);

	    if (filesList.isEmpty() && phoneNumberList.isEmpty()) {
		throw new AconexException("Phone number list not provided.");
	    }

	    if (phoneNumberList.isEmpty()) {
		phoneNumberList = fileUtility.readFiles(filesList);
	    }

	    if (!phoneNumberList.isEmpty()) {

		this.dictionaryService = new DictionaryServiceImpl();
		if (this.dictionaryFilePath != null) {
		    dictionaryService.load(this.dictionaryFilePath);
		    phoneNumberToWordsService
		    .setDictionaryService(dictionaryService);
		}

		phoneNumberWordMap = phoneNumberToWordsService
			.buildWords(phoneNumberList);

		ReportingService.displayReport(phoneNumberWordMap);

	    }
	} catch (IOException e) {
	    throw new AconexException(e.getMessage(), e);
	}

	return phoneNumberWordMap;
    }


    /**
     * @param filesList
     * @param phoneNumberList
     */
    public void processSystemInput(List<String> filesList,
	    List<String> phoneNumberList) {

	if (filesList.isEmpty()) {
	    System.out.println("No file arguments were found. ");
	    System.out.println("Please enter your phone numbers to continue.");
	    String line;
	    Scanner stdin = new Scanner(System.in);

	    while (stdin.hasNextLine()
		    && !(line = stdin.nextLine()).equals("C")) {
		System.out
			.println("Please enter another number or type 'C' to continue.");
		if (line != null && !line.trim().equals("")) {
		    phoneNumberList.add(line);
		}
	    }
	    stdin.close();
	}
    }

    /**
     * @param parameters
     * @param filesList
     * @param count
     * @throws AconexException
     * @throws FileNotFoundException
     */
    public void processArgumentsInput(String[] parameters,
	    List<String> filesList, int count) throws AconexException,
	    FileNotFoundException {
	if (parameters != null) {
	    
	    FileUtility fileUtility = new FileUtility();

	    for (String parameter : parameters) {

		if (parameter.equals("-d")) {
		    if (parameters.length == count + 2
			    && new File(parameters[count + 1]).isFile()) {
			this.dictionaryFilePath = parameters[count + 1];
			break;
		    } else {
			throw new AconexException("Invalid dictionary path.");
		    }
		}

		fileUtility.addFiles(filesList, parameter);
		count += 1;
	    }
	}
    }

}
