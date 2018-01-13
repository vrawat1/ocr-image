package com.fusion.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class NameDataSet {
	
	private Set<String> firstNamesDictionary = new HashSet<String>();
	private Set<String> lastNamesDictionary = new HashSet<String>();
	private Set<String> jobTitlesDictionary = new HashSet<String>();

	
	@Inject
	public NameDataSet(Properties props) {
		Pattern pattern = Pattern.compile("\\s+|,");
		String firstNames[] = pattern.split(props.getProperty("firstname").toLowerCase());
		String lastNames[] = pattern.split(props.getProperty("lastname").toLowerCase());
		
		Pattern patternJob = Pattern.compile(",");
		String jobTitles[] = patternJob.split(props.getProperty("jobtitles").toLowerCase());
		firstNamesDictionary = new HashSet<>(Arrays.asList(firstNames));
		lastNamesDictionary = new HashSet<>(Arrays.asList(lastNames));
		jobTitlesDictionary = new HashSet<>(Arrays.asList(jobTitles));
	}
	public Set<String> getFirstNamesDictionary() {
		return firstNamesDictionary;
	}
	public Set<String> getLastNamesDictionary() {
		return lastNamesDictionary;
	}
	public Set<String> getJobTitlesDictionary() {
		return jobTitlesDictionary;
	}
}
