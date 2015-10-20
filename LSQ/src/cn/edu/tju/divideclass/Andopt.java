package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.edu.tju.where.Where;

public class Andopt {
	public static void find_andopt( ){
		String sparqlString = null;
		String filePath = "G:\\QueryText_Where.txt";
		File file =new File("G:\\QueryText_AndOpt.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		int begin, end;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if((!sparqlString.contains("FILTER"))&&(!sparqlString.contains("UNION"))&&(!sparqlString.contains(" filter"))&&(!sparqlString.contains("filter "))&&(!sparqlString.contains("GRAPH"))&&(!sparqlString.contains("filter("))){
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
