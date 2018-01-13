/**
 * 
 */
package com.fusion.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.fusion.service.ProcessCommand;


/**
 * @author vijrawat
 *
 */
public class ImageConversionProcessCommandImpl implements ProcessCommand {

	private static Logger logger = Logger.getLogger(ImageConversionProcessCommandImpl.class);
	

	/* (non-Javadoc)
	 * @see com.taleo.ProcessCommand#execute(java.lang.String)
	 */
	@Override
	public String execute(final String inputFile) {
		//String tiffFilename = inputFile+".tiff"; 
		String tiffFilename = inputFile+".tiff";
//		ProcessBuilder builder = new ProcessBuilder(IMAGE_CONVERSION_COMMAND, 
//									"-density", "300", inputFile, 
//									"-depth", "8", 
//									"-strip", 
//									"-background", 
//									"white", 
//									"-alpha", 
//									"off", tiffFilename);
		
		ProcessBuilder builder = new ProcessBuilder(IMAGE_CONVERSION_COMMAND,
								inputFile, 
								"-colorspace", "RGB",  
								"-alpha", "off", 
								"-units", 
								"PixelsPerInch", 
								"-resample", 
								"600", 
								tiffFilename);
		

		try {
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
		return tiffFilename;
	}
}
