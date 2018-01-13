package com.fusion.parser;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobTitleExtractor {
	
	@Autowired
	private NameDataSet nameDataSet;
	
	private Pattern pattern;
	
	public JobTitleExtractor() {
		pattern = Pattern.compile("\\s+");
	}
	
	public String extract(String line) {
		String jobTitle = null;
		String words[] = pattern.split(line);
		for (int index=0; index < words.length; index++) {
			String origTitle = words[index];
			if (nameDataSet.getJobTitlesDictionary().contains(origTitle.toLowerCase())) {
				jobTitle = origTitle;
				break;
			}
		}
		return jobTitle;
	}
}
