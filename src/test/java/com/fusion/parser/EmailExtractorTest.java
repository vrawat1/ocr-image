package com.fusion.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

public class EmailExtractorTest {
	
	private EmailExtractor emailExtractor;

	@Before
	public void setUp() throws Exception {
		emailExtractor = new EmailExtractor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidEmails() {
		String emailTexts [] = validEmailProvider();
		for(String emailunderTest : emailTexts) {
			String extractedEmail = emailExtractor.extract(emailunderTest);
			System.out.println(emailunderTest + " :" + extractedEmail);
			Assert.isTrue(extractedEmail != null);
		}
	}
	
	@Test
	public void testInValidEmails() {
		String emailTexts [] = invalidEmailProvider();
		for(String emailunderTest : emailTexts) {
			String extractedEmail = emailExtractor.extract(emailunderTest);
			System.out.println(emailunderTest + " :" + extractedEmail);
			Assert.isTrue(extractedEmail == null);
		}
	}
	
	
	public String[] validEmailProvider() {
		return  new String[] { "my mail is mkyong@yahoo.com",
			"my mail is not  mkyong-100@yahoo.com", "my mail has been mkyong.100@yahoo.com",
			"Email : mkyong111@mkyong.com", "mkyong-100@mkyong.net",
			"mkyong.100@mkyong.com.au", "mkyong@1.com",
			"mkyong@gmail.com.com", "mkyong+100@gmail.com",
			"mkyong-100@yahoo-test.com" }; 
	}

	public String[] invalidEmailProvider() {
		return new String[] { "my mail is mkyong", "mkyong@.com.my",
			"mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
			".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
			"mkyong..2002@gmail.com", "mkyong.@gmail.com",
			"mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a"};
	}


}
