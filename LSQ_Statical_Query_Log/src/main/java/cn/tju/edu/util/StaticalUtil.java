package cn.tju.edu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StaticalUtil {
	public static void statical(String source, String dest) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(source));
			bufferedWriter = new BufferedWriter(new FileWriter(dest));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		
		String tempString = null;
		Integer str2Int = null;
		Integer count = null;
		
		System.out.println("begin to reading ...");
		
		try {
			while ((tempString=bufferedReader.readLine()) != null) {
				str2Int = Integer.parseInt(tempString.trim());
				
				if(hashMap.containsKey(str2Int)) {
					count = hashMap.get(str2Int);
					hashMap.put(str2Int, new Integer(count+1));
				} else {
					hashMap.put(str2Int, new Integer(1));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Set<Integer> triplePatterns = hashMap.keySet();
		try {
			for (Integer triplePattern : triplePatterns) {
				bufferedWriter.write(triplePattern.toString() + "\t" + hashMap.get(triplePattern) + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("end to write...");
		
	}
}
