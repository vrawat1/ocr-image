package com.fusion.service;

public interface ProcessCommand {
	public static final String storageDirectory="C:/vijay/OCR-POC/tesseractdir";

	public static final String TESSERA_COMMAND = "tesseract";
	
	public static final String IMAGE_CONVERSION_COMMAND = "magick";

	public String execute(final String inputFile);
}
