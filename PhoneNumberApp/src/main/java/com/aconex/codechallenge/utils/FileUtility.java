/**
 * 
 */
package com.aconex.codechallenge.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author Charith De Silva
 *
 */
public class FileUtility {
    
    private static final Logger LOGGER = Logger.getLogger(FileUtility.class.getName());

    /**
     * Read contents into a List for given files list.
     * 
     * @param filesList
     * @return list of lines.
     * @throws IOException
     */
    public List<String> readFiles(List<String> filesList) throws IOException {

	List<String> lines = new ArrayList<>();

	for (String file : filesList) {
	    lines.addAll(readFile(file));
	}

	return lines;
    }

    /**
     * Read contents into a List for given file.
     * 
     * @param lines
     * @throws IOException
     * @throws FileNotFoundException
     */
    public List<String> readResourceFile(String fileName) throws IOException {

	List<String> lines = new ArrayList<>();

	// Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	URL resource = classLoader.getResource(fileName);
	File file = null;
	
	if (resource != null) {
	    file = new File(resource.getFile());
	} else {
	    throw new FileNotFoundException("File " + fileName + " not found.");
	}

	try (Scanner scanner = new Scanner(file)) {

	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		lines.add(line);
	    }

	    scanner.close();

	}
	return lines;
    }
    
    /**
     * Read contents into a List for given file.
     * 
     * @param lines
     * @throws IOException
     * @throws FileNotFoundException
     */
    public List<String> readFile(String file) throws IOException {
	String currentLine = null;
	List<String> lines = new ArrayList<>();

	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    while ((currentLine = br.readLine()) != null) {
		lines.add(currentLine);
	    }
	}

	return lines;
    }
    
    /**
     * If file exists add to the list.
     * 
     * @param filesList
     * @param parameter
     * @throws FileNotFoundException
     */
    public void addFiles(List<String> filesList, String parameter)
	    throws FileNotFoundException {
	if (new File(parameter).isFile()) {
	    filesList.add(parameter);
	} else {
	    throw new FileNotFoundException("File " + parameter + " not found.");
	}
    }

    
}
