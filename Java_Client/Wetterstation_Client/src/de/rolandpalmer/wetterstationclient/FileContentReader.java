package de.rolandpalmer.wetterstationclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileContentReader {
	
	String file;
	
	public FileContentReader(String file) {
		this.file = file;
	}
	
	public String ReadFileContent() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(this.file));

		List<String> arrs = new ArrayList<String>();
		String line = "";
		
		while((line=br.readLine()) != null){
			arrs.add(line);
		}
		
		br.close();
		
		return arrs.toString();
	}
}
