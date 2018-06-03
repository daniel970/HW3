package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PMCounter {

	public HashMap<String, Integer> pmcount(HashMap<Integer, String> parsedMessage){
	//가져온 hashmap parsedMessage
	
	ArrayList<String> parsedMessageArrayList = new ArrayList<String>();
	//new arraylist name
	ArrayList<String> nameArraylist = new ArrayList<String>();
	//new hashmap(name) - key: name, value: 횟수
	HashMap<String, Integer> finalValue = new HashMap<String, Integer>();
	//new arraylist 중복제거한것들 추가하는 곳
	//new int 횟수
	int i=0;
	int j=0;
	int count = 1;
	String[] parsedMessageIntoArrayWithoutDuplicate;
	ArrayList<String> parsedMessageIntoArrayWithoutDuplicateOnlyName = new ArrayList<String>();
	
	
	parsedMessageArrayList.addAll( parsedMessage.values() ); //parsedMessageArrayList = parsedMessage를 arraylist로 바꿈
	String[] parsedMessageIntoArray = parsedMessageArrayList.toArray(new String[parsedMessageArrayList.size()]); //arraylist -> array
	//hashmap -> array
	
	
	//matcher로 ([] [] []) 이름값만 가져와서 arraylist(name)에 추가
	for(i = 0; i<parsedMessageIntoArray.length; i++) {
		if (parsedMessageIntoArray[i].matches("\\[.+\\]\\s\\[.+\\]\\s\\[.+\\]")) {
			String pattern = "\\[(.+)\\]\\s\\[(.+)\\]\\s\\[(.+)\\]";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(parsedMessageIntoArray[i]); //해당하는 라인
			
			if(m.find()) {
				nameArraylist.add(m.group(2)); // 이름값만 들어간 array(nameArraylist)
			}
			continue;
		}
	}

	
	//arraylist(name) 안에서 중복값 제거
	ArrayList<String> nameArraylistWithoutDuplicate = new ArrayList<String>();
	for (i=0;i<nameArraylist.size(); i++) {
		if(!nameArraylistWithoutDuplicate.contains(nameArraylist.get(i)))
			nameArraylistWithoutDuplicate.add(nameArraylist.get(i));
	} //중복값 제거한 nameArraylistWithoutDuplicate
	
	
	//가져온 카톡 기록에서 중복 제거(parsedMessageIntoArray)
	parsedMessageIntoArrayWithoutDuplicate = new HashSet<String>(Arrays.asList(parsedMessageIntoArray)).toArray(new String[0]);
	
	
	
	//출처: http://cpdev.tistory.com/41 [하루하나]
//parsedMessageIntoArrayWithoutDuplicate는 중복이 제거됨
	
	//count
	
	for(i = 0; i<parsedMessageIntoArrayWithoutDuplicate.length; i++) {
		if (parsedMessageIntoArrayWithoutDuplicate[i].matches("\\[.+\\]\\s\\[.+\\]\\s\\[.+\\]")) {
			String pattern = "\\[(.+)\\]\\s\\[(.+)\\]\\s\\[(.+)\\]";
			Pattern r2 = Pattern.compile(pattern);
			Matcher m2 = r2.matcher(parsedMessageIntoArrayWithoutDuplicate[i]); //해당하는 라인
			
			if(m2.find()) {
				parsedMessageIntoArrayWithoutDuplicateOnlyName.add(m2.group(2)); // 이름값만 들어간 array(nameArraylist)
			}
			continue;
		}
	}
	
	String[] parsedMessageIntoArrayWithoutDuplicateOnlyNameIntoArray = parsedMessageIntoArrayWithoutDuplicateOnlyName.toArray(new String[parsedMessageIntoArrayWithoutDuplicateOnlyName.size()]); //arraylist -> array
	
	String[] nameArrayWithoutDuplicate = nameArraylistWithoutDuplicate.toArray(new String[nameArraylistWithoutDuplicate.size()]); //nameArraylistWithoutDuplicate를 name3에 array로 저장
	
		for (i = 0; i < nameArraylistWithoutDuplicate.size(); i++) { //nameArraylistWithoutDuplicate = 이름 중복값 제거한 arraylist
		count = 0;
		for (j = 0; j < parsedMessageIntoArrayWithoutDuplicate.length; j++) {
			if (nameArrayWithoutDuplicate[i].equals(parsedMessageIntoArrayWithoutDuplicateOnlyNameIntoArray[j])) {
				count++;
			}

		}
		finalValue.put(nameArrayWithoutDuplicate[i],count);
		}


		Iterator<String> iterator = finalValue.keySet().iterator();
	    while (iterator.hasNext()) {
	        String key = (String) iterator.next();
	        System.out.print("key="+key);
	        System.out.println(" value="+finalValue.get(key));
	    }
	return finalValue;
	}
}