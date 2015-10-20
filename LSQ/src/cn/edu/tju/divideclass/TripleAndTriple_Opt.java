package cn.edu.tju.divideclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TripleAndTriple_Opt {//option 前为.且前方有{}
	// {?a a dbpedia-owl:Settlement}. ?a wgs84:lat ?point. OPTIONAL { ?a rdfs:label ?v0 . } OPTIONAL { ?a wgs84:lat ?v1 . } OPTIONAL { ?a wgs84:long ?v2 . } 
	public static void find_tripleandtriple_opt(){
		String sparqlString = null;
		String filePath = "G:\\QueryText_AndOpt_Opt.txt";
		File file =new File("G:\\QueryText_AndOpt_TripleAndTriple_Opt.txt");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWritter = new FileWriter("G:\\QueryText_AndOpt_TripleAndTriple_Opt.txt",true);
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
				if((count_dot>count_symbol)&&(count_symbol!=0)){
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
