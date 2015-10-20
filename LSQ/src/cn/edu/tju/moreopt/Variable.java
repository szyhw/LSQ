package cn.edu.tju.moreopt;

import java.util.ArrayList;
import java.util.HashSet;

public class Variable {
	public static ArrayList<HashSet<String>> get_variable(String string){
		String[] fragment= string.split("OPTIONAL");
		ArrayList<HashSet<String>> arrayset=new  ArrayList<HashSet<String>>();
		for(int i=0;i!=fragment.length;++i){
			String s=fragment[i];
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
							set.add(re);
							re="";
							break;
						}
					}
				}
			}
			arrayset.add(set);
		}
		return arrayset;
	}

}
