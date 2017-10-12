package de.rolandpalmer.wetterstationclient;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WetterstationClient {

	static File path = new File("c:\\temp\\test.txt");
	static File w1Path = new File("C:\\TEMP\\sys\\bus\\w1\\devices\\");
	static String w1File = "w1_slave";
	
	public static void main(String[] args) throws IOException {


		// get temperature
//		TemperatureReader rt = new TemperatureReader(path);
//		String temp = rt.GetTemperature();
//		System.out.println(temp);
		
		TemperatureReader w1 = new TemperatureReader(w1Path, w1File);
		List<String> w1Temp  = w1.GetTemperature();
		System.out.println(w1Temp);
		
		// save to database
		// fields: - id
		//         - date_time
		//         - temperature
		//         - committed
		
		// 
		
		// send to server
		
	}
	
}
