package com.fusion.service.impl;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fusion.service.OcrService;


//src/main/webapp/WEB-INF/applicationContext.xml
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/test/applicationContext-test.xml")
@ContextConfiguration("classpath*:applicationContext.xml")
public class OcrServiceImplTest {
	
	@Autowired
	private OcrService ocrService;
	
	@Value("${lookup.countries}")
	private String[] countries;
	
	@Value("${lookup.firstname}")
	private String[] firstnames;
	
	@Value("${lookup.lastname}")
	private String[] lastnames;
	
	@Value("${lookup.jobtitles}")
	private String[] jobtitles;
	
	@Autowired
	private PropertyPlaceholderConfigurer loader;
	
	@Before
	public void setUp() {
		String storageDirectory = "D:/vijay/OCR-POC/tesseractdir";
		assertNotNull(storageDirectory);
	}
	
	@Test
	public void testProcessPdf() {
		String outputText = ocrService.processImage("vijay_rawat_resume.pdf");
		
		Assert.assertNotNull(outputText);
	}
	
	@Test
	public void testProcessJPg() {
		String outputText = ocrService.processImage("bas_3.jpg");
		Assert.assertNotNull(outputText);
	}
	
}
