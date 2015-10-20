package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Select {
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
	public static void find_select(){
		
		String sparqlString = null;
		String filePath = "G:\\QueryText.txt";
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\Select.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if((sparqlString.contains("select ?")||sparqlString.contains("SELECT"))&&(calculate(sparqlString, "select ?")>1||calculate(sparqlString, "SELECT")>1)){
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
