package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertFormSymbole {
	public static void ChangeSymbol(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_TripleAndTriple_Opt.txt";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		int begin, end;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\datasource.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				int start_opt=sparqlString.toUpperCase().indexOf("OPTIONAL");
				int start_symbol=sparqlString.toUpperCase().indexOf("}");
				sparqlString=sparqlString.replace(sparqlString.charAt(start_symbol), ' ');
				String s1=sparqlString.substring(0, start_opt);
				String s2=sparqlString.substring(start_opt, sparqlString.length());
				String s=s1+"} "+s2;
				bufferWritter.write(s+"\n");
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
