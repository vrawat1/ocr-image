package com.fusion.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class SocialLinksExtractor {
	private Pattern pattern;
	public SocialLinksExtractor() {
		pattern = Pattern.compile("(?i)\\b((?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))");
	}
	
	public String extract(String line) {
		String website = null;
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			website = line.substring(start, end);
		}
		return website;
	}
}
