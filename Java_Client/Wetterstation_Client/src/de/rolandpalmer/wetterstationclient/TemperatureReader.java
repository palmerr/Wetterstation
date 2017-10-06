package de.rolandpalmer.wetterstationclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class TemperatureReader extends FileContentReader{

	public TemperatureReader(String file) {
		super(file);
	}

	public String GetTemperature() throws IOException {
		String fileContent = super.ReadFileContent();
		String strTemperature = null;
		
		String pattern = "batz";
		for(MatchResult r : findMatches(pattern, fileContent)) {
			strTemperature = r.group();
		}
		
		return strTemperature;
	}
	
	static Iterable<MatchResult> findMatches(String pattern, CharSequence s){
		List<MatchResult> results = new ArrayList<MatchResult>();
		
		for(Matcher m = Pattern.compile(pattern).matcher(s); m.find();)
			results.add(m.toMatchResult());
		return results;
		
	}
}
