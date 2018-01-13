package com.fusion.parser;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class NameExtractor {
	
	private Pattern pattern;
	
//	@Value("${lookup.firstname}")
//	private String[] firstnames;
//	
//	@Value("${lookup.lastname}")
//	private String[] lastnames;
	
	@Autowired
	private NameDataSet nameDataSet;
	
	public NameExtractor() {
		pattern = Pattern.compile("\\s+|,");
	}
	
	public FullName extractName(String nameText) {
		FullName name = null;
		String[] words = pattern.split(nameText);
		for (int index =0; index<words.length; index++) {
			String origWord =  words[index];
			String word = origWord.toLowerCase().trim();
			if (StringUtils.hasText(word)) {
				if (nameDataSet.getFirstNamesDictionary().contains(word)) {
					if (name == null) {
						name = new FullName();
					}	
					name.setFirstName(origWord);
				} else {
					if (name == null) {
						name = new FullName();
					}	
					name.setLastName(origWord);
				}
			}	
		}
		return name;
	}
}
