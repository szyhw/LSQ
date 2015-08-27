package cn.tju.edu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StringUtil {
	
	public static void replace(String replacement, String regex, String sourFile, String destFile) {
		File sourceFile = new File(sourFile);
		
		if(!sourceFile.exists()) {
			System.err.println("There is no source file.");
			return;
		}
		
		FileUtil.CompulsoryCreateFile(destFile);
		
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(sourceFile));
			bufferedWriter = new BufferedWriter(new FileWriter(destFile));
			
			String line = null;
			String replaceLine = null;
			while ((line = bufferedReader.readLine()) != null) {
				replaceLine = line.replaceAll(regex, replacement);
				bufferedWriter.write(replaceLine + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
