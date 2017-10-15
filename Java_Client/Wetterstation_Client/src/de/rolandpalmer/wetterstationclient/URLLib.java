package de.rolandpalmer.wetterstationclient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLLib {
	
	public static boolean sendURL(TempRecordSet tempRS) throws IOException {
		boolean bool=false;
		
		// create URL string
		//http://ftit17-webapp.azurewebsites.net/webdoc/temp.php?temp=10.2&timestamp=10:10:10:10%2015%20Sep%202017&dbg=1&token=89-123213d2&city=Friedrichshafen&lat=12,1234&long=45,1278
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://ftit17-webapp.azurewebsites.net/webdoc/temp.php?");
		
//		int id = tempRS.getId();
//		sb.append(id);
		
		String sensorID = tempRS.getSensorId();
		sb.append("sensorid=" + sensorID);
		double temperature = tempRS.getTemperatur();
		sb.append("&temp=" + temperature);
		LocalDateTime localDateTime = tempRS.getDateTime();
		sb.append("&timestamp=" + localDateTime);
		boolean isSend = tempRS.isSend();
		sb.append("&dbg=1");
//		sb.append("&senderID" + senderID);
//		sb.append("&city="+city);
//		sb.append("&long="+coordinatelong);
//		sb.append("&lat="+coordinatelat);
		sb.append(isSend);
		
		// System.out.println(sb.toString());
		
		// send URL String and get response 
		String response = null;
		URL u = new URL(sb.toString());
		URLConnection con = u.openConnection();
		InputStream is = con.getInputStream();
		BufferedInputStream bufferStream = new BufferedInputStream(is);
		//String str=null;
		byte[] buffer = new byte[1024*10];
		while((bufferStream.read(buffer)) != -1) {
			System.out.println(new String(buffer));
			response = response + buffer;
		}
		bufferStream.close();
		
		// Verify response
		// Transmission Ok = send temperature OK, return true
		// Transmission error = send temperature not ok, return false
		return getResponseStatus(response);
	}
	
	private static boolean getResponseStatus(String response) {
		
		Pattern template = Pattern.compile("Transmission: OK");
		Matcher matcher = template.matcher(response);
		if(matcher.find()) {
			return true;
		}
		else{
			return false;
		}
		
//		String strTemp = matcher.group(1);
//
//		return false;
	}
}
