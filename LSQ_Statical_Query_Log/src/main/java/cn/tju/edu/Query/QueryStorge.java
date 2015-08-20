package cn.tju.edu.Query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import cn.tju.edu.util.FileUtil;

public class QueryStorge {
	private TupleQuery query;
	private TupleQueryResult result;
	private RepositoryConnection connection;
	
	public QueryStorge(RepositoryConnection connection) {
		this.connection = connection;
	}
	
	private boolean printResultToFile(String filePath) {
		File resultFile = FileUtil.CompulsoryCreateFile(filePath);
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(resultFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		try {
			while(result.hasNext()) {
				BindingSet bs = result.next();
				
				try {
					for(String bindingName : bs.getBindingNames()) {
						bufferedWriter.write(bs.getBinding(bindingName).toString() + "\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						bufferedWriter.flush();
						bufferedWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void QueryToFile(String queryString, String resultFile) {
		try {
			query = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			try {
				result = query.evaluate();
			} catch (QueryEvaluationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printResultToFile(resultFile);
	}
	
}
