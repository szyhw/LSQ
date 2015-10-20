package cn.edu.tju.moreopt;

import java.util.ArrayList;
import java.util.HashSet;

public class OperateSet {
	public static boolean get_result(ArrayList<HashSet<String>> arrayset){
		int i;
		int j;
		int k;
		for(i=1;i!=arrayset.size()-1;++i){
			HashSet<String> result_intersect = new HashSet<String>();
			HashSet<String> result_union = new HashSet<String>();
			for(j=0;j!=i;++j){
				arrayset.get(0).addAll(arrayset.get(j));
			}
			for(k=i;k!=arrayset.size();++k){
				arrayset.get(i).retainAll(arrayset.get(k));
			}
			result_intersect=arrayset.get(i);
			result_union=arrayset.get(0);
			if((!result_union.containsAll(result_intersect))){
				return false;
			}
		}
		return true;
	}

}
