package cn.edu.tju.where;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExetractWhere {

	public static void ExetractWhere() {
		
		// TODO Auto-generated method stub
		String sparqlString = null;
		String filePath = "G:\\QueryText.txt";
		File file =new File("G:\\QueryText_Where.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		int begin, end;
		try {
			//int count=0;
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_Where.txt",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			while ((sparqlString=bufferedReader.readLine()) != null) {
				if(sparqlString.toUpperCase().contains("WHERE ")){
					begin = sparqlString.indexOf('\"');
					end = sparqlString.lastIndexOf('\"');
					if(begin < end){
						String q=Where.find_where(sparqlString);
						bufferWritter.write(q+"\n");
					}
				}
				//System.out.println(++count);
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
