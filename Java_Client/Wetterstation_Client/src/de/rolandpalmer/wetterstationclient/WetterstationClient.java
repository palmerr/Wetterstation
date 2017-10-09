package de.rolandpalmer.wetterstationclient;

import java.io.IOException;

public class WetterstationClient {

	static String path = "c:\\temp\\test.txt";
	
	public static void main(String[] args) throws IOException {

		// get temperature
		TemperatureReader rt = new TemperatureReader(path);
		String temp = rt.GetTemperature();
		System.out.println(temp);
		
		// save to database
		// fields: - id
		//         - date_time
		//         - temperature
		//         - committed
		
		// 
		
		// send to server
		
	}
	
}
