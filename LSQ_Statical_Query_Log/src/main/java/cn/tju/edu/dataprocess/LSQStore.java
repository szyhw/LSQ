package cn.tju.edu.dataprocess;

import java.io.File;
import java.io.IOException;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.nativerdf.NativeStore;

public class LSQStore {
	private Repository repository;
	private RepositoryConnection repositoryConnection;
	
	public LSQStore(String dataDirPath) {
		File dataDir = new File(dataDirPath);
		repository = new SailRepository(new NativeStore(dataDir));
		try {
			repositoryConnection = repository.getConnection();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addFromFile(String dataFilePath) {
		File data = new File(dataFilePath);
		try {
			repositoryConnection.add(data, "", RDFFormat.TURTLE);
		} catch (RDFParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RepositoryConnection getRepositoryConnection() {
		return repositoryConnection;
	}

	public void setRepositoryConnection(RepositoryConnection repositoryConnection) {
		this.repositoryConnection = repositoryConnection;
	}
	
}
