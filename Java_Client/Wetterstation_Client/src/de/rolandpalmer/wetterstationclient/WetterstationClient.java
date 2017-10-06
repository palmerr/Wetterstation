package de.rolandpalmer.wetterstationclient;

import java.io.IOException;

public class WetterstationClient {

	static String path = "c:\\temp\\test.txtt";
	
	public static void main(String[] args) throws IOException {

		// read file
		FileContentReader rfc = new FileContentReader(path);
		String str = rfc.ReadFileContent();
		System.out.println(str);
		
		
		TemperatureReader rt = new TemperatureReader(path);
		String temp = rt.GetTemperature();
		System.out.println(temp);
		
		//String strContent = rfc(path);
		
		// get temperature
		
		// save to database
		
		// send to server
		
	}
	
}
