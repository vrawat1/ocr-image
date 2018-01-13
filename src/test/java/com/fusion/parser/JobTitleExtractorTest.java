package com.fusion.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fusion.BaseTestCase;

import junit.framework.Assert;

public class JobTitleExtractorTest extends BaseTestCase {

	@Autowired
	private JobTitleExtractor jobTitleExtractor;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testExtractTitle1() {
		String input = "President & CEO";
		String title = jobTitleExtractor.extract(input);
		Assert.assertNotNull(title);
	}
	
	@Test
	public void testExtractTitle2() {
		String input = "Accountant";
		String title = jobTitleExtractor.extract(input);
		Assert.assertNotNull(title);
	}
	
	
}
