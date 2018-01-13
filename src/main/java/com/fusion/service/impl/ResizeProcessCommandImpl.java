package com.fusion.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.fusion.service.ProcessCommand;

public class ResizeProcessCommandImpl implements ProcessCommand {
	
	private static Logger logger = Logger.getLogger(ResizeProcessCommandImpl.class);

	@Override
	public String execute(String inputFile) {
		String extension = null;
		String outputfile = null;
		try {
			extension = FilenameUtils.getExtension(inputFile);
			outputfile = inputFile+"_" + "gray"+"."+extension;
			ProcessBuilder builder = new ProcessBuilder(IMAGE_CONVERSION_COMMAND, 
										inputFile, 
										"-colorspace", "gray", 
										outputfile);
			builder.directory(new File(storageDirectory));
			Process process = builder.start();
			int errCode = process.waitFor();
			if (errCode !=0 ) {
				System.out.println("Image Conversion Occurred");
				logger.error("Image Conversion Occurred");
				throw new RuntimeException("Image Conversion Occurred ErrorCode :"+errCode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Image Conversion Occurred", ex);
		} 
		return outputfile;
	}
}
