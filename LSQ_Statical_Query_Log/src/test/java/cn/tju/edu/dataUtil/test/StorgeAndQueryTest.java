package cn.tju.edu.dataUtil.test;

import org.junit.Test;

import cn.tju.edu.Query.QueryStorge;
import cn.tju.edu.dataUtil.Storge;

public class StorgeAndQueryTest {
	private static Storge storge = new Storge("/home/hxw/SesameStorage");
	private static QueryStorge query = new QueryStorge(storge.getConnection());
	
	@Test
	public void test() {
//		String queryString = "PREFIX lsqv:<http://lsq.aksw.org/vocab#>  PREFIX sp:<http://spinrdf.org/sp#> SELECT ?id ?text WHERE { ?id lsqv:endpoint ?ep . ?id sp:text ?text. FILTER NOT EXISTS { ?id lsqv:triplePatterns ?number } OPTIONAL { ?id lsqv} }";
//		String queryString = "PREFIX lsqv:<http://lsq.aksw.org/vocab#> SELECT (COUNT(?id) AS ?count) WHERE {  ?id lsqv:endpoint ?ep }";
//		String queryString = "PREFIX lsqv:<http://lsq.aksw.org/vocab#> SELECT (COUNT(?id) AS ?count) WHERE {  ?id lsqv:triplePatterns ?ep }";
		String queryString = "PREFIX lsqv:<http://lsq.aksw.org/vocab#>  PREFIX sp:<http://spinrdf.org/sp#> DESCRIBE <http://lsq.aksw.org/res/DBpedia-q4>";
		query.QueryToFile(queryString, "/home/hxw/Data/result");
	}

}
