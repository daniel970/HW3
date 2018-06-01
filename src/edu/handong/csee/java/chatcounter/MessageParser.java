package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {
	public void messageparse(ArrayList<String> brought) {
		
		ArrayList<String> loadedlines = brought; //bring arraylist
		String[] place = loadedlines.toArray(new String[loadedlines.size()]); //arraylist -> array
		
		HashMap<Integer, String> parse = new HashMap<Integer, String>();
		
		String currentYear= "";//2018
		String currentMonth= "";//06
		String currentDay= "";//01
		String currentHr="";//12
		String currentMin="";//42
		String currentId="";//JC
		String currentMessage="";//Hi everyone. How are you?
		String currentTime = ""; //201806011242
		String id = "";
		String time = "";
		String strMessage = "";
		String dateinFormat = ""; //20180601
		String dateinFormatPlusTime = ""; //201806011242
		String ampm = "";
		String si = "";
		int si2 = 0;
		int si3 = 0;
		int bun = 0;
		String bun2 = "";
		String namePlusStrMessage = ""; //[WS] Yes I think so..
		String namePlusStrMessage2 = ""; //[JC] Hi everyone. How are you?
		String finalParseSample = ""; //수정 - 201806011242 [JC] Hi everyone. How are you?
		String finalParseSample2 = ""; //수정
		int count = 0; //hashmap starts from 1
		

		
		int i = 0;
		for(i = 0; i<place.length; i++) { //가지고온 어레이 리스트 하나하나, 처음 라인부터 끝 라인까지
			
			if(place[i].matches("-+\\s[0-9]+.\\s[0-9]+.\\s[0-9]+.+")) { //--------------- 2018년 4월 26일 목요일 ---------------
				String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s+([0-9]+).+";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(place[i]); //해당하는 라인
				
				if(m.find()) {
					currentYear = m.group(1);
					currentMonth = m.group(2);
					currentDay = m.group(3);
				}		
				currentMonth = "0" + currentMonth; //4월 -> 04월
				dateinFormat = currentYear + currentMonth + currentDay;
				continue;
			}else if(place[i].matches("[0-9]+-[0-9]+-[0-9]+\\s[0-9]+:[0-9]+:[0-9]+,\\s.+,\".+\"")) { //2018-04-03 15:05:48, JC,"Hi everyone. How are you?"
				String pattern = "([0-9]+)-([0-9]+)-([0-9]+)\\s([0-9]+):([0-9]+):[0-9]+,\\s(.+),\"(.+)\"";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(place[i]); //해당하는 라인
				
				if(m.find()) {
					currentYear = m.group(1); //이곳 조금 다르게함(32/38)
					currentMonth = m.group(2);
					currentDay = m.group(3);
					currentHr = m.group(4);
					currentMin = m.group(5);
					currentId = m.group(6);
					currentMessage = m.group(7);
				}
				currentTime = "[" + currentYear + currentMonth + currentDay + currentHr + currentMin + "]";
				namePlusStrMessage2 = "[" + currentId + "]" + " " + "[" + currentMessage + "]";
				finalParseSample = currentTime + " " + namePlusStrMessage2;
				count++;
				parse.put(count, finalParseSample); //<count, [201806011242] [JC] [Hi everyone. How are you?]>
				continue;		
			}
			

			if(place[i].matches("\\[.+\\]\\s\\[.+\\]\\s.+")) { //[WS] [오전 7:16] Yes I think so..
				String pattern = "(\\[.+\\])\\s\\[(.+)\\]\\s(.+)";
				
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(place[i]); //해당하는 라인
				
				if(m.find()) {
					id = m.group(1); //[WC]
					time = m.group(2);
					strMessage = m.group(3);
				}
				
				if (time.matches(".+\\s[0-9]+:[0-9]+")) { //오전 7:16
					String timePattern = "(.+)\\s([0-9]+):([0-9]+)";
					Pattern r2 = Pattern.compile(timePattern);
					Matcher m2 = r2.matcher(time);
					
					if(m2.find()) {
						ampm = m2.group(1);
						si = m2.group(2); //string
						bun = Integer.parseInt(m2.group(3));
					}
					si2 = Integer.parseInt(si); //7 int로 받기, si2 = (int)si = 2
					if (ampm.equals("오후")) { //오후면 12추가
						si3 = 12 + si2;
						si = String.valueOf(si3);
					}else {
						si = String.valueOf(si2);
						if (si.equals("10") || si.equals("11") || si.equals("12")) {
							
						}else {
							si = "0" + si;
						}
					}
					if (bun < 10) {
						bun2 = "0" + String.valueOf(bun);
					}else {
						bun2 = String.valueOf(bun);
					}
				}
				
				namePlusStrMessage = id + " " + "[" + strMessage + "]";
				dateinFormatPlusTime = "[" + dateinFormat + si + bun2 + "]";
				finalParseSample2 = dateinFormatPlusTime + " " + namePlusStrMessage;
				count++;

				parse.put(count, finalParseSample2); //<count, [201806011242] [WS] [Yes I think so..]>
				
				continue;
			}
		}
		for (Integer num : parse.keySet()) { //checking if its parsed correctly
			System.out.println(parse.get(num));
		}
	
	}

}

