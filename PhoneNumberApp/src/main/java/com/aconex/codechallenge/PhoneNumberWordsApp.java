package com.aconex.codechallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aconex.codechallenge.exceptions.AconexException;

/**
 * @author chades
 *
 */
public class PhoneNumberWordsApp {

    private String dictionaryFilePath = "wordsEn.txt";

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
     * Check availability of the file. If file exists add to the list.
     * 
     * @param filesList
     * @param line
     * @throws FileNotFoundException
     */
    private void addFiles(List<String> filesList, String line)
	    throws FileNotFoundException {
	if (new File(line).isFile()) {
	    filesList.add(line);
	} else {
	    throw new FileNotFoundException("File " + line + " not found.");
	}
    }

}
