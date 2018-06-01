package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)throws IOException {
		MessageParser messageparser = new MessageParser();
		FileLoader fileloader = new FileLoader();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String filename = keyboard.next();
		String[]listOfFiles = filename.split(" ");

		for (int i=0; i<listOfFiles.length; i++) {
			ArrayList<String> brought = fileloader.fileLoad(listOfFiles[i]);
			messageparser.messageparse(brought);
		}
		keyboard.close();	
	}
}
