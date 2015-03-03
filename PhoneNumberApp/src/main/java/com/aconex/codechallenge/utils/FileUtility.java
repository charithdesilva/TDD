/**
 * 
 */
package com.aconex.codechallenge.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chades
 *
 */
public class FileUtility {

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

}
