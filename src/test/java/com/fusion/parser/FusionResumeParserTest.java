package com.fusion.parser;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fusion.BaseTestCase;

public class FusionResumeParserTest extends BaseTestCase{
	
	@Autowired
	FusionResumeParser fusionResumeParser;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseResume() {
		String textContents = "Robert P. Smith\r\nAccountant\r\n\r\n263 Main Street Hampton, MA\r\nPhone: 413-555-7688 Fax: 413-555-7689\r\nrpsmith@smith.com\r\n\r\nwww.smith.com\r\nwww.facebook.com";
		PersonalInfo personalInfo = fusionResumeParser.parseResume(textContents);
		System.out.println(personalInfo);
	}
	
	@Test
	public void testParseResume2() {
		String textContents = "Robert P. Smith\r\nPresident & CEO\r\n\r\n263 Main Street Hampton, MA\r\nPhone: 413-555-7688 Fax: 413-555-7689\r\nrpsmith@smith.com\r\n\r\nwww.smith.com\r\nwww.facebook.com";
		PersonalInfo personalInfo = fusionResumeParser.parseResume(textContents);
		System.out.println(personalInfo);
	}

}
