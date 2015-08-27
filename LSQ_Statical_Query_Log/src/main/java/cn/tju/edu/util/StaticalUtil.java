package cn.tju.edu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
	
	public static void staticalFeature(String source, String dest) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		if(!new File(source).exists()) {
			System.err.println("There is no such file.");
			return;
		}
		
		FileUtil.CompulsoryCreateFile(dest);
		
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
		
		Map<String, String> hashMap = new HashMap<String, String>();
		
		String feature = null;
		String features = null;
		String queryId = null;
		
		String line = null;
		String[] mapElements = null;
				
		System.out.println("begin to reading ...");
		
		try {
			while ((line=bufferedReader.readLine()) != null) {
				mapElements = line.split(" ");
				
				feature = mapElements[0];
				queryId = mapElements[1];
								
				if(hashMap.containsKey(queryId)) {
					features = hashMap.get(queryId);
					hashMap.put(queryId, features.concat(String.valueOf(feature.charAt(0))));
				} else {
					hashMap.put(queryId, String.valueOf(feature.charAt(0)));
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
		
		Set<String> queryIds = hashMap.keySet();
		try {
			for (String qId : queryIds) {
				bufferedWriter.write(qId + "\t" + hashMap.get(qId) + "\n");
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
	
	public static void CountFeature(String source, String dest) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		if(!new File(source).exists()) {
			System.err.println("There is no such file.");
			return;
		}
		
		FileUtil.CompulsoryCreateFile(dest);
		
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
		
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		
		String features = null;
		String featureMatch = null;
		Integer count = null;
		
		String line = null;
		String[] mapElements = null;
				
		System.out.println("begin to reading ...");
		
		try {
			while ((line=bufferedReader.readLine()) != null) {
				mapElements = line.split("\t");
				
				featureMatch = mapElements[1];
				
				features = featureMatch.replaceAll("OO+", "O").replaceAll("FF+", "F").replaceAll("UU+", "U");
								
				if(hashMap.containsKey(features)) {
					count = hashMap.get(features);
					hashMap.put(features, new Integer(count+1));
				} else {
					hashMap.put(features, new Integer(1));
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
		
		Set<String> featureSet = hashMap.keySet();
		try {
			for (String feature : featureSet) {
				bufferedWriter.write(feature + "\t" + hashMap.get(feature) + "\n");
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
