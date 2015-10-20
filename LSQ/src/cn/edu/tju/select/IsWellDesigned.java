package cn.edu.tju.select;

import java.util.ArrayList;
import java.util.HashSet;

public class IsWellDesigned {
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
	public static ArrayList<HashSet<String>> get_varible_selectbetweenwhere(String sparqlString){
		int count = calculate(sparqlString, "SELECT ");
		ArrayList<HashSet<String>> arrayset=new  ArrayList<HashSet<String>>();
		for(int i=0;i!=count;++i){
			int begin=sparqlString.indexOf("SELECT ");
			int end=sparqlString.indexOf("WHERE");
			int final_end=sparqlString.length();
			String s=sparqlString.substring(begin, end);
			HashSet<String> set=new HashSet<String>();
			for(int j=0;j!=s.length();++j){
				if(s.charAt(j)=='?'){
					String re="";
					for(int k=j+1;k!=s.length();++k){
						String sub=s.substring(k, k+1);
						if(!sub.equals(" ")&&!sub.equals(".")){
							re=re+sub;
						}
						//if(sub.equals(" ")||sub.equals(".")){
						else{
							set.add("?"+re);
							re="";
							break;
						}
					}
				}
			}
			arrayset.add(set);
			sparqlString=sparqlString.substring(end+6, final_end);
		}
		return arrayset;
	}
	public static void iswelldesigned(String sparqlString){
		sparqlString=sparqlString.toUpperCase();
		int sum=calculate(sparqlString, "SELECT ");
		
	}

}
