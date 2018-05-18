package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileLoader {
	public static void main(String[] args)throws IOException {
		try {
			System.out.print("write file name");
			Scanner keyboard = new Scanner(System.in);
			String filename = keyboard.nextLine();
			FileWriter fw = new FileWriter(filename);
			keyboard.close();
		}catch (IOException e) {
			System.out.print("Error" + e.getMessage());
		}
	}
}
