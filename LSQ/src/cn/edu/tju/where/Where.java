package cn.edu.tju.where;


public class Where {
	public static String find_where(String query){
		String str_standard=query.toUpperCase();
		int whereClauseBegin, whereClauseEnd;
		whereClauseBegin = str_standard.indexOf('{', str_standard.indexOf("WHERE"));
		whereClauseEnd = str_standard.lastIndexOf('}');
		return query.substring(whereClauseBegin+1, whereClauseEnd);
	}

}
