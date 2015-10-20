package cn.edu.tju.specialform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DivideByOpt {
	public static int calculate(String str, String substr) {
        String temp = str;
        int count = 0;
        int index = temp.indexOf(substr);
        while (index != -1) {
            temp = temp.substring(index + 1);
            index = temp.indexOf(substr);
            count++;
        }
        return count;
    }
	public static void find_moreopt(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_TripleAndTriple_Opt.txt";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_TripleAndTriple_Opt_MoreOpt.txt",true);
			FileWriter fileWritter1 = new FileWriter("G:\\QueryText_AndOpt_TripleAndTriple_Opt_SingleOpt.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			BufferedWriter bufferWritter1 = new BufferedWriter(fileWritter1);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if(calculate(sparqlString.toUpperCase(),"OPTIONAL")>1){
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
