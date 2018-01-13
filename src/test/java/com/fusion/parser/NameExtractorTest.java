package com.fusion.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fusion.BaseTestCase;

import junit.framework.Assert;

public class NameExtractorTest extends BaseTestCase {
	
	@Autowired
	private NameExtractor nameExtractor;

	@Before
	public void setUp() throws Exception {
		//nameExtractor = new NameExtractor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExtractName() {
		FullName fullName = nameExtractor.extractName("Peter M. Breining");
		System.out.println(fullName.getFirstName() +" :"+fullName.getLastName());
		Assert.assertTrue(fullName != null);
	}

}
