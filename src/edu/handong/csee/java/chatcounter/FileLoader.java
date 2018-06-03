package edu.handong.csee.java.chatcounter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/*Name:Doyoung Kim 21700082
 * Lecture: Java
 * Date: 2018-06-03
 * Description: Fileloading method
 */


public class FileLoader {
	private ArrayList<String> lines;


	public ArrayList<String> fileLoad(String listOfFiles) {
		String thisLine = null;
		this.lines = new ArrayList<String>();


		try {
	         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(listOfFiles), "UTF8"));
	         while ((thisLine = br.readLine()) != null) { // while loop begins here
	            lines.add(thisLine);
	        }
	        br.close();
	      	} catch (IOException e) {
	         e.printStackTrace();
	      	} 
		return lines;
	}

}

