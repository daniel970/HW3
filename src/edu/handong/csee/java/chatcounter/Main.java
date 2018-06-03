package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)throws IOException {
		MessageParser messageparser = new MessageParser();
		FileLoader fileloader = new FileLoader();
		PMCounter pmcounter = new PMCounter();
		
		HashMap<Integer, String> parsedMessage = new HashMap<Integer, String>();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String filename = keyboard.next();
		String[]listOfFiles = filename.split(" ");
		
		for (int i=0; i<listOfFiles.length; i++) {
			ArrayList<String> brought = fileloader.fileLoad(listOfFiles[i]);
			parsedMessage = messageparser.messageparse(brought);
			pmcounter.pmcount(parsedMessage); //PMCounter
		}
		
		
		keyboard.close();	
	}
}
