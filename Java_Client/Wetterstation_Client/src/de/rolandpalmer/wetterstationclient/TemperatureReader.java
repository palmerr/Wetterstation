package de.rolandpalmer.wetterstationclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.io.File;

public class TemperatureReader extends FileContentReader{
	
	String File;
	public TemperatureReader(File path, String file) {
		super(path);
		this.File = file;
	}
	

	public List<String> GetTemperature() throws IOException {
		//directory
		List<String> result = new ArrayList<String>();
		
		if(Path != null) {
			// directory implementation
			if(Path.exists()) {
				if(Path.isDirectory()) {
					for(File dir : Path.listFiles()) {
						File filePath = new File(dir + "\\" + File); 
						this.setPath(filePath);
						
						String content = this.ReadFileContent();

						Pattern template = Pattern.compile(".+t=(.+)");
						Matcher matcher = template.matcher(content);
						matcher.find();
						
						String strTemp = matcher.group(1);
						String sensorID = dir.getName();
						Double sensorTemp = (Double.valueOf(strTemp))/1000;

						result.add(sensorID+":"+sensorTemp.toString());
					}
				}
			}
		} 
		
		return result;

		
		
		
//		String fileContent = super.ReadFileContent();
//		String strTemperature = null;
//		
//		String pattern = "batz";
//		for(MatchResult r : findMatches(pattern, fileContent)) {
//			strTemperature = r.group();
//		}
		
//		return strTemperature;
	}
	
	static Iterable<MatchResult> findMatches(String pattern, CharSequence s){
		List<MatchResult> results = new ArrayList<MatchResult>();
		
		for(Matcher m = Pattern.compile(pattern).matcher(s); m.find();)
			results.add(m.toMatchResult());
		return results;
		
	}
}
