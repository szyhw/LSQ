package cn.tju.edu.dataUtil.test;

import org.junit.Test;

import cn.tju.edu.Query.QueryStorge;
import cn.tju.edu.dataUtil.Storge;

public class StorgeAndQueryTest {
	private static Storge storge = new Storge("/home/hxw/SesameStorage");
	private static QueryStorge query = new QueryStorge(storge.getConnection());
	
	@Test
	public void test() {
		String queryString = "PREFIX lsqv:<http://lsq.aksw.org/vocab#> SELECT (COUNT(?id) AS ?count) WHERE {  ?id lsqv:endpoint ?ep }";
		query.QueryToFile(queryString, "/home/hxw/Data/result");
	}

}
