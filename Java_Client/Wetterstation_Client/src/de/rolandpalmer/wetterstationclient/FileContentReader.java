package de.rolandpalmer.wetterstationclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileContentReader {
	
	File Path;
	
	public void setPath(File path) {
		Path = path;
	}

	/**
	 * read a file and return teh content as a string array
	 * 
	 * @param file
	 */
	public FileContentReader(File path) {
		this.Path = path;
	}
	

	public String ReadFileContent() throws IOException {

		List<String> arrs = new ArrayList<String>();
		String content=null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(Path));
	
			String line = "";
			
			while((line=br.readLine()) != null){
				arrs.add(line);
				content = content + line;
			}
			
			br.close();
			
		}
		catch(Exception e) {
			
		}
		
		//return arrs.toString();
		return content;
	}
}
