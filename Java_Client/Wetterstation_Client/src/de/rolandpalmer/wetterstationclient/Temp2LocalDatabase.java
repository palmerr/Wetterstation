package de.rolandpalmer.wetterstationclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


public class Temp2LocalDatabase {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public static void writeDatabase(String StationIDTemp) throws Exception{
		try {
			String[] splitString = StationIDTemp.split(":");
			String stationID = splitString[0];
			double temperature = Double.parseDouble(splitString[1]);
			
			// setup db connection
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ftit17","root","");
			
			// prepare & execute statement
			preparedStatement = connect.prepareStatement("INSERT INTO temperature (station_id, temperature) VALUES (?,?)");
			preparedStatement.setString(1, stationID);
			preparedStatement.setDouble(2, temperature);
			preparedStatement.executeUpdate();
			
			// close connection
			preparedStatement.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<TempRecordSet> readDatabase() throws Exception{
		List<TempRecordSet> dblist = new ArrayList<TempRecordSet>();

		try {
			// setup db connection
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ftit17","root","");
			
			// prepare & execute statement
			preparedStatement = connect.prepareStatement("SELECT * FROM temperature WHERE send=0");
			resultSet = preparedStatement.executeQuery();
			
			// DateTime format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.S");
			
			while(resultSet.next()) {
//				System.out.println(resultSet.getString("id"));
//				System.out.println(resultSet.getString("station_id"));
//				System.out.println(resultSet.getString("temperature"));
//				System.out.println(resultSet.getString("date_time"));
//				System.out.println(resultSet.getString("send"));
				int id = Integer.parseInt(resultSet.getString("id"));
				String sensorid = resultSet.getString("station_id");
				double temperature = Double.valueOf(resultSet.getString("temperature"));
				String strDateTime = resultSet.getString("date_time");
				LocalDateTime datetime = LocalDateTime.parse(strDateTime, formatter);
				boolean send = Boolean.parseBoolean(resultSet.getString("send"));
				
				TempRecordSet trs = new TempRecordSet(id, sensorid, temperature, datetime, send);
				dblist.add(trs);
			}

			// close connection
			preparedStatement.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return dblist;
	}
	
	public static void updateDatabase(int id) throws Exception{
		try {
			
			// setup db connection
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ftit17","root","");
			
			// prepare & execute statement
			preparedStatement = connect.prepareStatement("UPDATE temperature SET send=1 WHERE id=?");
			preparedStatement.setInt(1,  id);
			preparedStatement.executeUpdate();
			
			// close connection
			preparedStatement.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
