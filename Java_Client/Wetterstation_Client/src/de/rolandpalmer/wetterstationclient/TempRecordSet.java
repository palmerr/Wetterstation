package de.rolandpalmer.wetterstationclient;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TempRecordSet {
	private int Id;
	private String SensorId;
	private double Temperatur;
	private LocalDateTime DateTime;
	private boolean Send = false;

	// DateTime format
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.S");
				
	public TempRecordSet(int id, String sensorid, double temperature, LocalDateTime datetime, boolean send){
		this.Id=id;
		this.SensorId=sensorid;
		this.Temperatur=temperature;
		this.DateTime = datetime;
		this.Send=send;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getSensorId() {
		return SensorId;
	}
	public void setSensorId(String sensorId) {
		this.SensorId = sensorId;
	}
	public double getTemperatur() {
		return Temperatur;
	}
	public void setTemperatur(double temperatur) {
		this.Temperatur = temperatur;
	}
	public LocalDateTime getDateTime() {
		return DateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.DateTime = dateTime;
	}
	public boolean isSend() {
		return Send;
	}
	public void setSend(boolean send) {
		this.Send = send;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(Integer.toString(this.Id));
		sb.append(";");
		sb.append(this.SensorId);
		sb.append(";");
		sb.append(Double.toString(this.Temperatur));
		sb.append(";");
		sb.append(this.DateTime.format(formatter));
		sb.append(";");
		sb.append(Boolean.toString(this.Send));
		
		return sb.toString();
	}
	
}
