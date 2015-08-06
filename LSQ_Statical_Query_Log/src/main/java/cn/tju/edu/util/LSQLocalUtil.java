package cn.tju.edu.util;

import java.io.File;
import java.io.IOException;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.nativerdf.NativeStore;

public class LSQLocalUtil {
	
	public void CountTriplePattern(String path) {
		File dataDir = new File(path);
		SailRepository repository = new SailRepository(new NativeStore(dataDir));
		RepositoryConnection repositoryConnection = null;
		
		TupleQuery tupleQuery = null;
		TupleQueryResult tupleQueryResult = null;
		
		File dataFile = new File("E:\\LSQ-BM.ttl");
		String queryString = "PREFIX lsqv:<http://lsq.aksw.org/vocab#> SELECT (COUNT(?id) AS ?count) WHERE {  ?id lsqv:endpoint ?ep }";
		
		try {
			repository.initialize();
			repositoryConnection = repository.getConnection();
			repositoryConnection.add(dataFile, "", RDFFormat.TURTLE);
			System.out.println("begin query:");
			tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			tupleQueryResult = tupleQuery.evaluate();
			
			while(tupleQueryResult.hasNext()) {
				BindingSet bindingSet = tupleQueryResult.next();
				
				for(String string : bindingSet.getBindingNames()) {
					System.out.println(string + ":" + bindingSet.getBinding(string));
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
		} catch (RDFParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public void addDataFile(String dataPath) {
//		
//	}
//	
//	public Repository getRepository(String dataDirPath) {
//		File dataDir = new File(dataDirPath);
//		SailRepository repository = new SailRepository(new NativeStore(dataDir));
//		
//		try {
//			repository.initialize();
//		} catch (RepositoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return repository;
//	}
}
