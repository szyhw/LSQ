package cn.tju.edu.dataprocess;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class LSQQuery {
	private RepositoryConnection connection;
	
	private TupleQuery tupleQuery;
	private TupleQueryResult result;
		
	public LSQQuery(RepositoryConnection connection) {
		this.connection = connection;
	}
	
	public TupleQueryResult query(String queryString) {
		try {
			tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			result = tupleQuery.evaluate();
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
		
		return result;
	}
}
