package cn.tju.edu.dataUtil;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelldesignUtil {
	public static boolean isWelldesign(String queryString) {
		String whereClause = null;
		String upperString = queryString.toUpperCase();
		
		int whereClauseBegin, whereClauseEnd;
		
		whereClauseBegin = upperString.indexOf('{', upperString.indexOf("WHERE"));
		whereClauseEnd = upperString.lastIndexOf('}');
		whereClause = upperString.substring(whereClauseBegin, whereClauseEnd+1);
				
		upperString = whereClause.toUpperCase();
		
		try {
			travelBGP(upperString, upperString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	private static int findTheLittleBraceEnd(String bagPatterns, int start) {
		int end = bagPatterns.length();
		
		char tempChar;
		int i, depth = 1;
		
		if(bagPatterns.charAt(start) != '(')
			try {
				throw new Exception("There is something error in the substring.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		for(i=start+1; i<end; i++) {
			if(depth == 0)
				break;
			
			tempChar = bagPatterns.charAt(i);
			if(tempChar == '(') {
				depth ++;
			} else if(tempChar == ')') {
				depth --;
			}
		}
		
		return i;
	}
	
	private static int findTheBraceEnd(String bagPatterns, int start) {
		int end = bagPatterns.length();
		
		char tempChar;
		int i, depth = 1;
		
		if(bagPatterns.charAt(start) != '{')
			try {
				throw new Exception("There is something error in the substring.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		for(i=start+1; i<end; i++) {
			if(depth == 0)
				break;
			
			tempChar = bagPatterns.charAt(i);
			if(tempChar == '{') {
				depth ++;
			} else if(tempChar == '}') {
				depth --;
			}
		}
		
		return i;
	}
	
	private static int findTheBraceEndWithOptional(String bagPatterns, int start, String wholeWhereClause) throws Exception {
		int end = bagPatterns.length();
		
		Pattern optionalPattern = Pattern.compile("^ *OPTIONAL *\\{");
		Matcher matcher = null;
		
		start = findTheBraceEnd(bagPatterns, start);
		
		while (start < end) {
			matcher = optionalPattern.matcher(bagPatterns.substring(start+1));
			
			if(matcher.find()) {
				start = findTheBraceEnd(bagPatterns, start + matcher.end());
			} else {
				break;
			}
		}
		
		return start;
	}
	
	private static boolean isSafe(String bagPattern, ArrayList<Integer> optionalList) {
		Pattern filterPattern = null;
		Matcher filterMatcher = null;
		
		filterPattern = Pattern.compile("FILTER.*\\(");
		filterMatcher = filterPattern.matcher(bagPattern);
		
		int position;
		ArrayList<Integer> filterList = new ArrayList<Integer>();
		while (filterMatcher.find()) {
			position = filterMatcher.end()-1;
		}
		
		return true;
	}
	
	private static boolean isInnerOptionalWellDesigned(String bagPattern, String wholeWhereClause) {
		Pattern innerOptionalPattern = null;
		Matcher innerOptionalMatcher = null;
		
		innerOptionalPattern = Pattern.compile("OPTIONAL *\\{");
		innerOptionalMatcher = innerOptionalPattern.matcher(bagPattern);
		
		int position = 0;
		ArrayList<Integer> optionalList = new ArrayList<Integer>();
		while (innerOptionalMatcher.find(position)) {
			position = innerOptionalMatcher.end()-1;			
			optionalList.add(position);
			position = findTheBraceEnd(bagPattern, position);
			optionalList.add(position);			
		}
		
		if(!isSafe(bagPattern, optionalList))
			return false;
		
		return true;
	}
	
	private static boolean isOuterOptionalWellDesigned(String bagPattern, String wholeWhereClause) {
		int oneBgpEnd = findTheBraceEnd(bagPattern, 0);
		
		if(!isInnerOptionalWellDesigned(bagPattern.substring(0, oneBgpEnd), wholeWhereClause))
			return false;
		
		Pattern outerOptionalPattern = null;
		Matcher outerOptionalMatcher = null;
		
		outerOptionalPattern = Pattern.compile("\\} *OPTIONAL *\\{");
		outerOptionalMatcher = outerOptionalPattern.matcher(bagPattern);
		
		int start, end;
		boolean isOptionalWellDesigned = true;
		while (outerOptionalMatcher.find()) {
			
		}
		
//		int groupCount = outerOptionalMatcher.groupCount();

//		if(groupCount == 0) {
//			return isSafe(bagPattern);
//		} else {
//			int i, endPrePattern, startOptionalPattern, endOptionalPattern;
//			
//			for(i=0; i<groupCount; i++) {
//				if(optionalMatcher.find(i)) {
//					endPrePattern = optionalMatcher.start();
//					
//					if(!isSafe(bagPattern.substring(0, endPrePattern)))
//						return false;
//					
//					startOptionalPattern = optionalMatcher.end()-1;
//					endOptionalPattern = findTheBraceEnd(bagPattern, startOptionalPattern);
//					if(!isSafe(bagPattern.substring(startOptionalPattern, endOptionalPattern)))
//							return false;
//				} else {
//					try {
//						throw new Exception("Error in the count number of the optional. bagPattern: " + bagPattern + " the whole where clause:" + wholeWhereClause);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}

		return true;
	}
	
	private static boolean travelBGP(String bagPatterns, String wholeWhereClause) throws Exception {
		Pattern firstPattern = null;
		Pattern andPattern = null;
		
		firstPattern = Pattern.compile("\\{ *\\{");
//		unionAndPattern = Pattern.compile("( *UNION *\\{)|( *\\{)");
		andPattern = Pattern.compile(" *\\{");
		
		Matcher firstMatcher = null;
		Matcher andMatcher = null;
				
		boolean isWellDesign = true;
		
		firstMatcher = firstPattern.matcher(bagPatterns);
		
		if(!firstMatcher.lookingAt()) {
			isWellDesign = isOuterOptionalWellDesigned(bagPatterns, wholeWhereClause);
		} else {
			int start = 1, end = 0;
			
			andMatcher = andPattern.matcher(bagPatterns.substring(1));
			while (andMatcher.lookingAt()) {
				start += andMatcher.end();
				end = findTheBraceEndWithOptional(bagPatterns, start-1, wholeWhereClause);
				isWellDesign = isWellDesign && travelBGP(bagPatterns.substring(start-1, end).trim(), wholeWhereClause);
				start = end;
				andMatcher = andPattern.matcher(bagPatterns.substring(start));
			}
		}
		
		return isWellDesign;
	}
}
