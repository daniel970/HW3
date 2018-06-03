package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Name:Doyoung Kim 21700082
 * Lecture: Java
 * Date: 2018-06-03
 * Description: Counting messages
 */

public class PMCounter {

	public HashMap<String, Integer> pmcount(HashMap<Integer, String> parsedMessage){
	//hashmap parsedMessage
	
	ArrayList<String> parsedMessageArrayList = new ArrayList<String>();
	//new arraylist name
	ArrayList<String> nameArraylist = new ArrayList<String>();
	//new hashmap(name) - key: name, value: times
	HashMap<String, Integer> finalValue = new HashMap<String, Integer>();
	//new arraylist deleted duplicated
	int i=0;
	int j=0;
	int count = 1;
	String[] parsedMessageIntoArrayWithoutDuplicate;
	ArrayList<String> parsedMessageIntoArrayWithoutDuplicateOnlyName = new ArrayList<String>();
	
	
	parsedMessageArrayList.addAll( parsedMessage.values() ); //parsedMessageArrayList = parsedMessage -> arraylist
	String[] parsedMessageIntoArray = parsedMessageArrayList.toArray(new String[parsedMessageArrayList.size()]); //arraylist -> array
	//hashmap -> array
	
	
	//matcher ([] [] [])  arraylist(name) adds only name
	for(i = 0; i<parsedMessageIntoArray.length; i++) {
		if (parsedMessageIntoArray[i].matches("\\[.+\\]\\s\\[.+\\]\\s\\[.+\\]")) {
			String pattern = "\\[(.+)\\]\\s\\[(.+)\\]\\s\\[(.+)\\]";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(parsedMessageIntoArray[i]); //applicable line
			
			if(m.find()) {
				nameArraylist.add(m.group(2)); // array(nameArraylist)
			}
			continue;
		}
	}

	
	//arraylist(name) deleted duplicated
	ArrayList<String> nameArraylistWithoutDuplicate = new ArrayList<String>();
	for (i=0;i<nameArraylist.size(); i++) {
		if(!nameArraylistWithoutDuplicate.contains(nameArraylist.get(i)))
			nameArraylistWithoutDuplicate.add(nameArraylist.get(i));
	} // nameArraylistWithoutDuplicate: deleted duplicated
	
	
	//(parsedMessageIntoArray): deleted duplicated
	parsedMessageIntoArrayWithoutDuplicate = new HashSet<String>(Arrays.asList(parsedMessageIntoArray)).toArray(new String[0]);

	//http://cpdev.tistory.com/41
	
//parsedMessageIntoArrayWithoutDuplicate: deleted duplicated
	
	//count
	
	for(i = 0; i<parsedMessageIntoArrayWithoutDuplicate.length; i++) {
		if (parsedMessageIntoArrayWithoutDuplicate[i].matches("\\[.+\\]\\s\\[.+\\]\\s\\[.+\\]")) {
			String pattern = "\\[(.+)\\]\\s\\[(.+)\\]\\s\\[(.+)\\]";
			Pattern r2 = Pattern.compile(pattern);
			Matcher m2 = r2.matcher(parsedMessageIntoArrayWithoutDuplicate[i]); //applicable line
			
			if(m2.find()) {
				parsedMessageIntoArrayWithoutDuplicateOnlyName.add(m2.group(2)); // array(nameArraylist) has only name
			}
			continue;
		}
	}
	
	String[] parsedMessageIntoArrayWithoutDuplicateOnlyNameIntoArray = parsedMessageIntoArrayWithoutDuplicateOnlyName.toArray(new String[parsedMessageIntoArrayWithoutDuplicateOnlyName.size()]); //arraylist -> array
	
	String[] nameArrayWithoutDuplicate = nameArraylistWithoutDuplicate.toArray(new String[nameArraylistWithoutDuplicate.size()]); 
	
		for (i = 0; i < nameArraylistWithoutDuplicate.size(); i++) { //nameArraylistWithoutDuplicate =  arraylist deleted duplicated
		count = 0;
		for (j = 0; j < parsedMessageIntoArrayWithoutDuplicate.length; j++) {
			if (nameArrayWithoutDuplicate[i].equals(parsedMessageIntoArrayWithoutDuplicateOnlyNameIntoArray[j])) {
				count++;
			}

		}
		finalValue.put(nameArrayWithoutDuplicate[i],count);
		}


	//	Iterator<String> iterator = finalValue.keySet().iterator();
	  //  while (iterator.hasNext()) {
	    //    String key = (String) iterator.next();
	      //  System.out.print("key="+key);
	        //System.out.println(" value="+finalValue.get(key));
	    //}
	return finalValue;
	}
}