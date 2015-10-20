package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OneOpt {
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
	public static void find_oneopt(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_NormalForm.txt";
		File file =new File("G:\\QueryText_AndOpt_NormalForm_OneOpt.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_NormalForm_OneOpt.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if(calculate(sparqlString.toUpperCase(),"OPTIONAL")==1){
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
