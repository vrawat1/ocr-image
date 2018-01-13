package com.fusion.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fusion.BaseTestCase;

import junit.framework.Assert;

public class SocialLinksExtractorTest extends BaseTestCase {
	
	@Autowired
	private SocialLinksExtractor socialLinksExtractor;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExtract() {
		String line = "My website is www.basmedical.com";
		String websiteLink = socialLinksExtractor.extract(line);
		System.out.println("websiteLink :" +websiteLink);
		Assert.assertTrue(websiteLink != null);
	}
	
	@Test
	public void testExtractExactMatch() {
		String line = "www.basmedical.com";
		String websiteLink = socialLinksExtractor.extract(line);
		System.out.println("websiteLink :" +websiteLink);
		Assert.assertTrue(websiteLink != null);
	}
	
	@Test
	public void testExtractMail() {
		String line = "rpsmith@basmedical.com";
		String mailLink = socialLinksExtractor.extract(line);
		System.out.println("mailLink :" +mailLink);
		Assert.assertTrue(mailLink == null);
	}
	
	
	@Test
	public void testExtractMiddle() {
		String line = "My website is www.basmedical.com but not in the end";
		String websiteLink = socialLinksExtractor.extract(line);
		System.out.println("websiteLink :" +websiteLink);
		Assert.assertTrue(websiteLink != null);
	}
}
