package edu.handong.csee.java.chatcounter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class FileLoader {
	private ArrayList<String> lines;


	public ArrayList<String> fileLoad(String listOfFiles) {
		String thisLine = null;
		this.lines = new ArrayList<String>();


		try {
	         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(listOfFiles), "UTF8"));
	         while ((thisLine = br.readLine()) != null) { // while loop begins here
	            lines.add(thisLine);
	           // System.out.println(thisLine);
	        }
	        br.close();
	      	} catch (IOException e) {
	         e.printStackTrace();
	      	} // end while 
			//	System.out.println(lines); //메세지의 내용을 lines라는 arraylist에 저장
		return lines;
	}

}

