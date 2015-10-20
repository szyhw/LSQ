package cm.edu.tju.select;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OptSearch {
	public static void find_opt(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_Select_s.txt";
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		int begin, end;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_Select_s_Opt.txt",true);
			FileWriter fileWritter1 = new FileWriter("G:\\QueryText_Select_s_NoOpt.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			BufferedWriter bufferWritter1 = new BufferedWriter(fileWritter1);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if((sparqlString.contains("OPTIONAL"))||(sparqlString.contains("optional"))){
					bufferWritter.write(sparqlString+"\n");
				}
				else{
					bufferWritter1.write(sparqlString+"\n");
				}
			}
			bufferWritter.close();
			bufferWritter1.close();
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
