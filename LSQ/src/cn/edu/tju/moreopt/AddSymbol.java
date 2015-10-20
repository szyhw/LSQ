package cn.edu.tju.moreopt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddSymbol {
	public static void addsymbol(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_NormalForm_MoreOpt.txt";
		File file =new File("G:\\QueryText_AndOpt_NormalForm_MoreOpt_Final.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_NormalForm_MoreOpt_Final.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				
				String s=sparqlString.trim();
				int start_opt=s.toUpperCase().indexOf("OPTIONAL");
				String sub_front=s.substring(0,start_opt);
				String sub_back=s.substring(start_opt,s.length());
				if(!s.startsWith("{")){
					s="{"+sub_front+"}"+sub_back;
				}
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
