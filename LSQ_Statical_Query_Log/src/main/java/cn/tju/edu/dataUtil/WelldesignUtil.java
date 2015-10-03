package cn.tju.edu.dataUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelldesignUtil {
	private static int totalCount = 0;
	private static int wellCount = 0;
	private static int notWellCount = 0;
	private static int unionCount = 0;
	private static int subQueryCount = 0;

	public static boolean isWelldesign(String queryString) {
		totalCount++;
		String whereClause = null;
		String upperString = queryString.toUpperCase();

		int whereClauseBegin, whereClauseEnd;

		whereClauseBegin = upperString.indexOf('{', upperString.indexOf("WHERE"));
		whereClauseEnd = upperString.lastIndexOf('}');

		BufferedWriter wellDesignedBufferedWriter = null;
		BufferedWriter unWellDesignedBufferedWriter = null;
		BufferedWriter unionBufferedWriter = null;
		BufferedWriter subQueryBufferedWriter = null;

		try {
			FileWriter wellDesignedFileWriter = new FileWriter("/home/hanxingwang/Data/SearchResult/WellDesigned",
					true);
			FileWriter unWellDesignedfileWriter = new FileWriter("/home/hanxingwang/Data/SearchResult/UnWellDesigned",
					true);
			FileWriter unionFileWriter = new FileWriter("/home/hanxingwang/Data/SearchResult/NotUnionFree", true);
			FileWriter subQueryWriter = new FileWriter("/home/hanxingwang/Data/SearchResult/subQuery", true);

			wellDesignedBufferedWriter = new BufferedWriter(wellDesignedFileWriter);
			unWellDesignedBufferedWriter = new BufferedWriter(unWellDesignedfileWriter);
			unionBufferedWriter = new BufferedWriter(unionFileWriter);
			subQueryBufferedWriter = new BufferedWriter(subQueryWriter);

			if (whereClauseBegin == -1) {
				wellDesignedBufferedWriter.write(queryString + "\n");
				wellCount++;
				System.out.println("total: " + totalCount + " wellcount:" + wellCount + " notwellcout:" + notWellCount
						+ " UnionCount:" + unionCount + " subqueryCount: " + subQueryCount);

				return true;
			}
			
			if (upperString.indexOf(" WHERE ") != upperString.lastIndexOf(" WHERE ")) {
				subQueryBufferedWriter.write(queryString + "\n");
				subQueryCount++;
				System.out.println("total: " + totalCount + " wellcount:" + wellCount + " notwellcout:" + notWellCount
						+ " UnionCount:" + unionCount + " subqueryCount: " + subQueryCount);
			}

			whereClause = upperString.substring(whereClauseBegin, whereClauseEnd + 1);

			upperString = whereClause.toUpperCase();

			if (upperString.contains("UNION")) {
				unionBufferedWriter.write(queryString + "\n");
				unionCount++;
				System.out.println("total: " + totalCount + " wellcount:" + wellCount + " notwellcout:" + notWellCount
						+ " UnionCount:" + unionCount + " subqueryCount: " + subQueryCount);
				return false;
			}

			if (travelBGP(upperString, upperString)) {
				wellDesignedBufferedWriter.write(queryString + "\n");
				wellCount++;
				System.out.println("total: " + totalCount + " wellcount:" + wellCount + " notwellcout:" + notWellCount
						+ " UnionCount:" + unionCount + " subqueryCount: " + subQueryCount);
				return true;
			} else {
				unWellDesignedBufferedWriter.write(queryString + "\n");
				notWellCount++;
				System.out.println("total: " + totalCount + " wellcount:" + wellCount + " notwellcout:" + notWellCount
						+ " UnionCount:" + unionCount + " subqueryCount: " + subQueryCount);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				unionBufferedWriter.flush();
				wellDesignedBufferedWriter.flush();
				unWellDesignedBufferedWriter.flush();
				subQueryBufferedWriter.flush();

				unionBufferedWriter.close();
				wellDesignedBufferedWriter.close();
				unWellDesignedBufferedWriter.close();
				subQueryBufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("total: " + totalCount + " wellcount:" + wellCount + " notwellcout:" + notWellCount
				+ " UnionCount:" + unionCount + "subqueryCount: " + subQueryCount);
		return true;
	}

	private static int findTheLittleBraceEnd(String bagPatterns, int start) {
		int end = bagPatterns.length();

		char tempChar;
		int i, depth = 1;

		if (bagPatterns.charAt(start) != '(')
			try {
				throw new Exception("There is something error in the substring.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		for (i = start + 1; i < end; i++) {
			if (depth == 0)
				break;

			tempChar = bagPatterns.charAt(i);
			if (tempChar == '(') {
				depth++;
			} else if (tempChar == ')') {
				depth--;
			}
		}

		return i;
	}

	private static int findTheBraceEnd(String bagPatterns, int start) {
		int end = bagPatterns.length();

		char tempChar;
		int i, depth = 1;

		if (bagPatterns.charAt(start) != '{')
			try {
				throw new Exception("There is something error in the substring.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		for (i = start + 1; i < end; i++) {
			if (depth == 0)
				break;

			tempChar = bagPatterns.charAt(i);
			if (tempChar == '{') {
				depth++;
			} else if (tempChar == '}') {
				depth--;
			}
		}

		return i;
	}

	private static int findTheBraceEndWithOptional(String bagPatterns, int start, String wholeWhereClause)
			throws Exception {
		int end = bagPatterns.length();

		Pattern optionalPattern = Pattern.compile("^ *OPTIONAL *\\{");
		Matcher matcher = null;

		start = findTheBraceEnd(bagPatterns, start);

		while (start < end) {
			matcher = optionalPattern.matcher(bagPatterns.substring(start + 1));

			if (matcher.find()) {
				start = findTheBraceEnd(bagPatterns, start + matcher.end());
			} else {
				break;
			}
		}

		return start;
	}

	private static ArrayList<String> getVariable(String string) {
		char tempChar;
		int i = 0, length = string.length();
		boolean flag = false;
		StringBuffer sb = new StringBuffer("");
		ArrayList<String> variablesList = new ArrayList<String>();
		for (i = 0; i < length; i++) {
			tempChar = string.charAt(i);
			if (!flag) {
				if (tempChar == '?') {
					flag = true;
					sb = new StringBuffer("");
				}
			} else {
				if ((tempChar >= 'a' && tempChar <= 'z') || (tempChar >= 'A' && tempChar <= 'Z')
						|| (tempChar >= '0' && tempChar <= '9') || tempChar == '_')
					sb.append(tempChar);
				else {
					flag = false;
					variablesList.add(new String(sb));
				}

			}
		}

		return variablesList;
	}

	private static boolean isSafe(String bagPattern, ArrayList<Integer> optionalList) {
		Pattern filterPattern = null;
		Matcher filterMatcher = null;

		filterPattern = Pattern.compile("FILTER[^\\(]*\\(");
		filterMatcher = filterPattern.matcher(bagPattern);

		int start, end, position = 0;
		boolean flag = true;
		Iterator<Integer> iterator = null;
		ArrayList<Integer> filterList = new ArrayList<Integer>();
		while (filterMatcher.find(position)) {
			position = filterMatcher.end() - 1;

			iterator = optionalList.iterator();
			while (iterator.hasNext()) {
				start = iterator.next();
				end = iterator.next();

				if (position > start && position < end) {
					flag = false;
				}
			}

			if (flag) {
				filterList.add(position);
				position = findTheLittleBraceEnd(bagPattern, position);
				filterList.add(position);
			}
		}

		iterator = filterList.iterator();
		ArrayList<String> filterVariablesList = null;
		ArrayList<String> outFilterVariablesList = null;
		while (iterator.hasNext()) {
			filterVariablesList = new ArrayList<String>();
			outFilterVariablesList = new ArrayList<String>();

			start = iterator.next();
			end = iterator.next();

			filterVariablesList.addAll(getVariable(bagPattern.substring(start, end)));
			outFilterVariablesList.addAll(getVariable(bagPattern.substring(0, start)));

			if (!outFilterVariablesList.containsAll(filterVariablesList))
				return false;
		}

		return true;
	}

	private static boolean isInnerOptionalWellDesigned(String bagPattern, String wholeWhereClause) throws Exception {
		Pattern innerOptionalPattern = null;
		Matcher innerOptionalMatcher = null;

		innerOptionalPattern = Pattern.compile("OPTIONAL *\\{");
		innerOptionalMatcher = innerOptionalPattern.matcher(bagPattern);

		int position = 0;
		ArrayList<Integer> optionalList = new ArrayList<Integer>();
		while (innerOptionalMatcher.find(position)) {
			position = innerOptionalMatcher.end() - 1;
			optionalList.add(position);
			position = findTheBraceEnd(bagPattern, position);
			optionalList.add(position);
		}

		if (!isSafe(bagPattern, optionalList))
			return false;

		int start, end;
		Iterator<Integer> iterator = null;
		iterator = optionalList.iterator();
		while (iterator.hasNext()) {
			start = iterator.next();
			end = iterator.next();

			if (!travelBGP(bagPattern.substring(start, end), wholeWhereClause))
				return false;

			// process P = Q OPT R
			String Pstring, Qstring, Rstring, outString;

			Pstring = bagPattern.substring(0, end);
			Qstring = bagPattern.substring(0, start);
			Rstring = bagPattern.substring(start, end);

			int before = wholeWhereClause.indexOf(Pstring);
			int after = before + Pstring.length();

			outString = wholeWhereClause.substring(0, before) + wholeWhereClause.substring(after);

			ArrayList<String> insideRVariables = new ArrayList<String>();
			ArrayList<String> outsideVariables = new ArrayList<String>();
			ArrayList<String> insideQVariables = new ArrayList<String>();

			insideRVariables = getVariable(Rstring);
			outsideVariables = getVariable(outString);
			insideQVariables = getVariable(Qstring);

			String tempString = null;
			Iterator<String> iteratorString = null;
			iteratorString = insideRVariables.iterator();
			while (iteratorString.hasNext()) {
				tempString = iteratorString.next();
				if (outsideVariables.contains(tempString))
					if (!insideQVariables.contains(tempString))
						return false;
			}
		}

		return true;
	}

	private static boolean isOuterOptionalWellDesigned(String bagPattern, String wholeWhereClause) throws Exception {
		int oneBgpEnd = findTheBraceEnd(bagPattern, 0);

		if (!isInnerOptionalWellDesigned(bagPattern.substring(0, oneBgpEnd), wholeWhereClause))
			return false;

		Pattern outerOptionalPattern = null;
		Matcher outerOptionalMatcher = null;

		outerOptionalPattern = Pattern.compile("\\} *OPTIONAL *\\{");
		outerOptionalMatcher = outerOptionalPattern.matcher(bagPattern);

		String Pstring, Qstring, Rstring, outString;

		ArrayList<String> insideRVariables = new ArrayList<String>();
		ArrayList<String> outsideVariables = new ArrayList<String>();
		ArrayList<String> insideQVariables = new ArrayList<String>();

		int start, end;
		int position = 0;
		while (outerOptionalMatcher.find(position)) {
			start = outerOptionalMatcher.end() - 1;
			end = findTheBraceEnd(bagPattern, start);

			position = end - 1;

			if (!travelBGP(bagPattern.substring(start, end), wholeWhereClause))
				return false;

			Pstring = bagPattern.substring(0, end);
			Qstring = bagPattern.substring(0, start);
			Rstring = bagPattern.substring(start, end);

			int before = wholeWhereClause.indexOf(Pstring);
			int after = before + Pstring.length();

			outString = wholeWhereClause.substring(0, before) + wholeWhereClause.substring(after);

			insideRVariables = getVariable(Rstring);
			outsideVariables = getVariable(outString);
			insideQVariables = getVariable(Qstring);

			String tempString = null;
			Iterator<String> iteratorString = null;
			iteratorString = insideRVariables.iterator();
			while (iteratorString.hasNext()) {
				tempString = iteratorString.next();
				if (outsideVariables.contains(tempString))
					if (!insideQVariables.contains(tempString))
						return false;
			}
		}

		return true;
	}

	private static boolean travelBGP(String bagPatterns, String wholeWhereClause) throws Exception {
		Pattern firstPattern = null;
		Pattern andPattern = null;

		firstPattern = Pattern.compile("\\{ *\\{");
		// unionAndPattern = Pattern.compile("( *UNION *\\{)|( *\\{)");
		andPattern = Pattern.compile(" *\\{");

		Matcher firstMatcher = null;
		Matcher andMatcher = null;

		boolean isWellDesign = true;

		firstMatcher = firstPattern.matcher(bagPatterns);

		if (!firstMatcher.lookingAt()) {
			isWellDesign = isOuterOptionalWellDesigned(bagPatterns, wholeWhereClause);
		} else {
			int start = 1, end = 0;

			andMatcher = andPattern.matcher(bagPatterns.substring(1));
			while (andMatcher.lookingAt()) {
				start += andMatcher.end();
				end = findTheBraceEndWithOptional(bagPatterns, start - 1, wholeWhereClause);
				isWellDesign = isWellDesign
						&& travelBGP(bagPatterns.substring(start - 1, end).trim(), wholeWhereClause);
				start = end;
				andMatcher = andPattern.matcher(bagPatterns.substring(start));
			}
		}

		return isWellDesign;
	}
}