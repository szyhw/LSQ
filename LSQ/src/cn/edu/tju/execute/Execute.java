package cn.edu.tju.execute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import cm.edu.tju.select.OptSearch;
import cn.edu.tju.divideclass.Andopt;
import cn.edu.tju.divideclass.ConvertFormSymbole;
import cn.edu.tju.divideclass.MoreOpt;
import cn.edu.tju.divideclass.NoOpt;
import cn.edu.tju.divideclass.NormalForm;
import cn.edu.tju.divideclass.OneOpt;
import cn.edu.tju.divideclass.Opt;
import cn.edu.tju.divideclass.Select;
import cn.edu.tju.divideclass.SelectSimple;
import cn.edu.tju.divideclass.TripleAndTriple_Opt;
import cn.edu.tju.moreopt.AddSymbol;
import cn.edu.tju.moreopt.OperateSet;
import cn.edu.tju.moreopt.Variable;
import cn.edu.tju.moreopt.WellDesign;
import cn.edu.tju.specialform.DivideByOpt;
import cn.edu.tju.specialform.WellDesign_Special;

public class Execute {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Select.find_select();
		//NoOpt.find_noopt();
		//Opt.find_opt();
		//System.out.println("abcd".charAt(3));
		//TripleAndTriple_Opt.find_tripleandtriple_opt();
		//NormalForm.find_normalform();
		//OneOpt.find_oneopt();
		//NoOpt.find_noopt();
		//MoreOpt.find_moreopt();
		//AddSymbol.addsymbol();
		//WellDesign.find_welldesign();
		//Conver2Normal.convert2normal();
		//DivideByOpt.find_moreopt();
		//WellDesign_Special.find_welldesign();
		//Select.find_select();
		//SelectSimple.find_selectsimple();
		//OptSearch.find_opt();
		
		ConvertFormSymbole.ChangeSymbol();
		
		/*
		String sparqlString="SELECT ?name ?email FROM <http://www.w3.org/People/Berners-Lee/card> WHERE {     {      SELECT DISTINCT ?person ?name WHERE {          ?person foaf:name ?name        } ORDER BY ?name LIMIT 10 OFFSET 10    }     OPTIONAL { ?person foaf:mbox ?email } }";
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
		System.out.println(arrayset);*/
	}
}
