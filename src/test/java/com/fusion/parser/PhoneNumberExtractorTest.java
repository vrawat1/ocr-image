package com.fusion.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneNumberExtractorTest {
	
	private PhoneNumberExtractor phoneNumberExtractor;
	
	@Before
	public void setUp() throws Exception {
		phoneNumberExtractor = new PhoneNumberExtractor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExtractPhoneNumber() {
		String phoneText = "(650) 235 4000";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}
	
	@Test
	public void testExtractPhoneNumberWithTexts() {
		String phoneText = "Vijay rawat Ph : (650) 235 4000";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}
	
	@Test
	public void testExtractPhoneNumberFormat1() {
		String phoneText = "Vijay rawat Ph : 650-235-4000";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}
	
	@Test
	public void testExtractPhoneNumberFormat2() {
		String phoneText = "Vijay rawat Ph : 650.235.4000";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}

	@Test
	public void testExtractPhoneNumberFormat3() {
		String phoneText = "Vijay rawat Ph : 6502354000";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}
	
	
	@Test
	public void testInvalidPhoneNumber() {
		String phoneText = "Vijay rawat Ph : 94402-25236";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}
	
	@Test
	public void testInvalidPhoneNumber2() {
		String phoneText = "Vijay rawat Ph : 94402-252";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone == null);
	}
	
	@Test
	public void testPhoneNumber3() {
		String phoneText = "Vijay rawat Ph : +91-9953257996";
		String phone = phoneNumberExtractor.extractPhoneNumber(phoneText);
		System.out.println(phoneText + " :="+phone);
		Assert.assertTrue(phone != null);
	}
}
