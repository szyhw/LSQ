package cn.tju.edu.util;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class LSQHTTPUtil {
	
	public static void main(String[] args) {
		String endpoint = "http://lsq.aksw.org/sparql";
		HTTPRepository repository = new HTTPRepository(endpoint, "");
		RepositoryConnection repositoryConnection = null;
		try {
			repository.initialize();
			repositoryConnection = repository.getConnection();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = "PREFIX lsqv:<http://lsq.aksw.org/vocab#> SELECT ?id WHERE {  ?id lsqv:endpoint <http://dbpedia.org/sparql> }";
		try {
			TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, query);
			TupleQueryResult tupleQueryResult = tupleQuery.evaluate();
			int i = 0;
			while(tupleQueryResult.hasNext()) {
				BindingSet bindingSet = tupleQueryResult.next();
				
				for(String bindingString : bindingSet.getBindingNames()) {
					System.out.println(++i);
					System.out.println(bindingString + bindingSet.getBinding(bindingString));
				}
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
