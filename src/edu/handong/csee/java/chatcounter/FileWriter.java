package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.io.BufferedWriter;
import java.util.Iterator;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
	public void filewrite(HashMap<String, Integer> something){
		//String encoding = new java.io.OutputStreamWriter(System.out).getEncoding();
		//System.out.println("encoding check: " + encoding);
		
		String startWrite = "kakao_id, count \r\n";
		String wordToWrite = "";
		
		try {
			String CSVFile = "C:/git/csv1.csv";
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CSVFile), "MS949"));
			
			Iterator<String> iterator = something.keySet().iterator();
			writer.write(startWrite);
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
		        String name = key;
		        Integer number = something.get(key);
		        
		        wordToWrite = name + " , " + number + " \r\n";
		        writer.write(wordToWrite);
		    }
			writer.close();
		}
		 catch (IOException e) {
			 e.printStackTrace();
		 }
	}
}
