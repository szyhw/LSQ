package cn.edu.tju.moreopt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class WellDesign {
	public static void find_welldesign(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_NormalForm_MoreOpt_Final.txt";
		int count=0;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_NormalForm_MoreOpt_WellDsigned.txt",true);
			FileWriter fileWritter1 = new FileWriter("G:\\QueryText_AndOpt_NormalForm_MoreOpt_NonWellDsigned.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			BufferedWriter bufferWritter1 = new BufferedWriter(fileWritter1);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				ArrayList<HashSet<String>> arrayset=Variable.get_variable(sparqlString);
				System.out.println(++count);
				if(OperateSet.get_result(arrayset)){
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
