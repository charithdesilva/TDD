/**
 * 
 */
package com.aconex.codechallenge.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static final Logger LOGGER = Logger.getLogger(FileUtility.class
	    .getName());

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

	InputStream in = getFileInputStream(fileName);

	if (in != null) {
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String line;
	    while ((line = br.readLine()) != null) {
		lines.add(line);
	    }
	} else {
	    throw new FileNotFoundException("File " + fileName + " not found.");
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

    /**
     * Returns input stream for given file
     * 
     * @param fileName
     * @return
     */
    public InputStream getFileInputStream(String fileName) {
	return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
