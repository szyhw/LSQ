package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NoOpt {
	public static void find_noopt(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt.txt";
		File file =new File("G:\\QueryText_AndOpt_NoOpt.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		int begin, end;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_NoOpt.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if((!sparqlString.contains("OPTIONAL"))&&(!sparqlString.contains("optional"))&&(!sparqlString.contains("SELECT"))&&(!sparqlString.contains("select ?"))){
						bufferWritter.write(sparqlString+"\n");
				}
			}
			bufferWritter.close();
			System.out.println("end");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
