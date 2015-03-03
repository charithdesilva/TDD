package com.aconex.codechallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aconex.codechallenge.exceptions.AconexException;
import com.aconex.codechallenge.service.DictionaryService;
import com.aconex.codechallenge.service.DictionaryServiceImpl;

/**
 * @author chades
 *
 */
public class PhoneNumberWordsApp {

    private String dictionaryFilePath = null;
    private DictionaryService dictionaryService = null;


    /**
     * Entry point for word-generator app.
     * 
     * @param parameters
     * @throws AconexException
     */
    public void generateWords(String[] parameters) throws AconexException {
	List<String> filesList = new ArrayList<>();
	List<String> phoneNumberList = new ArrayList<>();
	FileUtility fileUtility = new FileUtility();
	PhoneNumberWordsGenerator phoneNumberWordsGenerator = new PhoneNumberWordsGenerator();

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
		}

		phoneNumberWordsGenerator.setDictionaryService(dictionaryService);
		Map<String, List<String>> phoneNumberWordMap = phoneNumberWordsGenerator.buildWords(
			phoneNumberList, phoneNumberWordsGenerator);

		if (!phoneNumberWordMap.isEmpty()) {

		    System.out
			    .println("================================================");

		    for (String key : phoneNumberWordMap.keySet()) {
			System.out.println("Phone Number [" + key + "]");
			int suggestion = 1;
			for (String word : phoneNumberWordMap.get(key)) {
			    System.out.println("           Suggestion ["
				    + suggestion + "] : " + word);
			    suggestion ++;
			}
		    }
		    System.out
			    .println("================================================");
		}

	    }
	} catch (IOException e) {
	    throw new AconexException(e.getMessage(), e);
	}
    }


    /**
     * @param filesList
     * @param phoneNumberList
     */
    public void processSystemInput(List<String> filesList,
	    List<String> phoneNumberList) {

	if (filesList.isEmpty()) {
	    String line;
	    Scanner stdin = new Scanner(System.in);

	    while (stdin.hasNextLine() && !(line = stdin.nextLine()).equals("")) {

		phoneNumberList.add(line);
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

		addFiles(filesList, parameter);
		count += 1;
	    }
	}
    }

    /**
     * If file exists add to the list.
     * 
     * @param filesList
     * @param parameter
     * @throws FileNotFoundException
     */
    private void addFiles(List<String> filesList, String parameter)
	    throws FileNotFoundException {
	if (new File(parameter).isFile()) {
	    filesList.add(parameter);
	} else {
	    throw new FileNotFoundException("File " + parameter + " not found.");
	}
    }

}
