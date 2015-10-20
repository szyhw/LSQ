package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NormalForm {
	public static void find_normalform(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_Opt.txt";
		File file =new File("G:\\QueryText_AndOpt_NormalForm.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_NormalForm.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				int end=sparqlString.toUpperCase().indexOf("OPTIONAL");
				String subString=sparqlString.substring(0, end);
				int count_dot=0;
				int count_symbol=0;
				for(int i=0;i!=subString.length();++i)
				{
					if(subString.charAt(i)=='.'){
						++count_dot;
					}
					if(subString.charAt(i)=='}'){
						++count_symbol;
					}
				}
				if((count_dot<=count_symbol)||(count_symbol==0)){
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
