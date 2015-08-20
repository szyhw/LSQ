package cn.tju.edu.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static File CompulsoryCreateDirectory(String path) {
		File file = new File(path);
		
		if(file.exists()) {
			file.delete();
		}
		
		file.mkdir();
		
		return file;
	}
	
	public static File IfNotExistThenCreateDirectory(String path) {
		File file = new File(path);
		
		if(file.exists()) {
			return file;
		}
		
		file.mkdirs();
		
		return file;
	}
	
	public static File CompulsoryCreateFile(String path) {
		File file = new File(path);
		
		if(file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file;
	}
}
