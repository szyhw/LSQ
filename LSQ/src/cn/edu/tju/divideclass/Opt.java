package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Opt {
	public static void find_opt(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt.txt";
		File file =new File("G:\\QueryText_AndOpt_Opt.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_Opt.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if(sparqlString.toUpperCase().contains("OPTIONAL")){
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
