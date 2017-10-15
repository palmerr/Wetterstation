package de.rolandpalmer.wetterstationclient;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WetterstationClient {

	static File path = new File("c:\\temp\\test.txt");
	static File w1Path = new File("C:\\TEMP\\sys\\bus\\w1\\devices\\");
	static String w1File = "w1_slave";
	
	public static void main(String[] args) throws Exception {


		// get temperature
		TemperatureReader w1 = new TemperatureReader(w1Path, w1File);
		List<String> w1Temp  = w1.GetTemperature();
		
		// write temperatures to database
		for (String str : w1Temp) {
			try {
				Temp2LocalDatabase.writeDatabase(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// get not send records from database
		List<TempRecordSet> Records2Send = Temp2LocalDatabase.readDatabase();
		
		// send not send records to url and set them to send=1
		for(TempRecordSet tmpRS : Records2Send) {

			if(URLLib.sendURL(tmpRS)) {
				// set recordset send to true
				
			}
		}
	}
}
