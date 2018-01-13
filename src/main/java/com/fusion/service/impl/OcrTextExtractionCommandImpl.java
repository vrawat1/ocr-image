package com.fusion.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.fusion.service.ProcessCommand;


public class OcrTextExtractionCommandImpl implements ProcessCommand {
	
	private static Logger logger = Logger.getLogger(OcrTextExtractionCommandImpl.class);
	
	@Override
	public String execute(String inputFile) {
		ProcessBuilder builder = new ProcessBuilder(TESSERA_COMMAND, inputFile, inputFile, "-l", "custom+eng");
		String outputbase = null;
		try {
			builder.directory(new File(storageDirectory));
			Process process = builder.start();
			int errCode = process.waitFor();
			if (errCode !=0 ) {
				System.out.println("Text Extraction Error Occurred");
				logger.error("Text Extraction Error Occurred");
				throw new RuntimeException("Text Extraction Error Occurred, ErrorCode :"+errCode);
			}
			outputbase = storageDirectory+File.separator+inputFile+".txt";
			//outputbase = tiffFilename+".txt";
			//outputText = getOutputText(outputbase);
		} catch (Exception ex) {  
			ex.printStackTrace();
			throw new RuntimeException("Text Extraction Error Occurred", ex);
		} 
		return outputbase;
	}

}
