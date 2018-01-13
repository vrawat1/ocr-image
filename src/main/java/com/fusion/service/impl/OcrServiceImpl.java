package com.fusion.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fusion.ocr.rest.ImageDocument;
import com.fusion.parser.FusionResumeParser;
import com.fusion.parser.PersonalInfo;
import com.fusion.service.OcrService;
import com.fusion.service.ProcessCommand;

import sun.misc.BASE64Decoder;

@Component
public class OcrServiceImpl implements OcrService {
	
	private static Logger logger = Logger.getLogger(OcrServiceImpl.class);
	
	private String IMAGE_CONVERSION_COMMAND = "magick";
	private String TESSERA_COMMAND = "tesseract";
	
	private String storageDirectory="C:/vijay/OCR-POC/tesseractdir";
	
	private String pathExecutableDirectory = "C:/Program Files/ImageMagick-7.0.7-Q16";
	
	private Map<String, List<ProcessCommand>> processCommands = new HashMap<String, List<ProcessCommand>>();
	
	@Autowired
	private FusionResumeParser fusionResumeParser;
	
	public OcrServiceImpl() {
	}
	
	@Override
	public String processImage(String file) {
		String outputFile = null;
		String inputfile = file;
		String outputText = null;
		String fileExtension = FilenameUtils.getExtension(file);
		List<ProcessCommand> prcCommands = processCommands.get(fileExtension);
		if (prcCommands == null) {
			prcCommands = processCommands.get("default");
		}
		if (prcCommands!= null && !prcCommands.isEmpty()) {
			for (ProcessCommand proc : prcCommands) {
				inputfile = proc.execute(inputfile);
			}
			outputFile = inputfile;
			try {
				outputText = getOutputText(outputFile);
			} catch (IOException ex) {
				logger.error("Error occurred while generating output file", ex);
				throw new RuntimeException("Error occurred while generating output file", ex);
			}
		}
		return outputText;
	}


//	private String convertTiffSuppportImage(String file) {
//		String tiffFilename = file+".tiff"; 
//		ProcessBuilder builder = new ProcessBuilder(IMAGE_CONVERSION_COMMAND, 
//									"-density", "300", file, 
//									"-depth", "8", 
//									"-strip", 
//									"-background", 
//									"white", 
//									"-alpha", 
//									"off", tiffFilename);
//
//		try {
//			builder.directory(new File(storageDirectory));
//			Process process = builder.start();
//			int errCode = process.waitFor();
//			if (errCode !=0 ) {
//				System.out.println("Image Conversion Occurred");
//				logger.error("Image Conversion Occurred");
//				throw new RuntimeException("Image Conversion Occurred ErrorCode :"+errCode);
//			}
//		} catch (IOException  | InterruptedException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("Image Conversion Occurred", ex);
//		} 
//		return tiffFilename;
//	}
//	
//	private String extractOCRCommand(String tiffFilename) {
//		ProcessBuilder builder = new ProcessBuilder(TESSERA_COMMAND, tiffFilename, tiffFilename);
//		String outputbase = null;
//		String outputText = null;
//		try {
//			builder.directory(new File(storageDirectory));
//			Process process = builder.start();
//			int errCode = process.waitFor();
//			if (errCode !=0 ) {
//				System.out.println("Text Extraction Error Occurred");
//				logger.error("Text Extraction Error Occurred");
//				throw new RuntimeException("Text Extraction Error Occurred, ErrorCode :"+errCode);
//			}
//			outputbase = storageDirectory+File.separator+tiffFilename+".txt";
//			//outputbase = tiffFilename+".txt";
//			outputText = getOutputText(outputbase);
//		} catch (IOException  | InterruptedException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("Text Extraction Error Occurred", ex);
//		} 
//		return outputText;
//	}

	private String getOutputText(String outputbase) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        FileInputStream inputStream;
        try {
        	inputStream = new FileInputStream(outputbase);
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();	
    }
	
	@Override
	public String saveImage(MultipartFile file) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File newFile = null;
		try {
			inputStream = file.getInputStream();
			newFile = new File(storageDirectory + File.separator+ file.getOriginalFilename());
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile.getName();
	}

	public Map<String, List<ProcessCommand>> getProcessCommands() {
		return processCommands;
	}

	public void setProcessCommands(Map<String, List<ProcessCommand>> processCommands) {
		this.processCommands = processCommands;
	}

	@Override
	public File saveImage(ImageDocument document) {
		String content="/9j/4AAQSkZJRgABAgAAZABkAAD/7AARRHVja3kAAQAEAAAAPAAA/+4ADkFkb2JlAGTAAAAAAf/bAIQABgQEBAUEBgUFBgkGBQYJCwgGBggLDAoKCwoKDBAMDAwMDAwQDA4PEA8ODBMTFBQTExwbGxscHx8fHx8fHx8fHwEHBwcNDA0YEBAYGhURFRofHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8f/8AAEQgAsQE2AwERAAIRAQMRAf/EAKoAAQACAwEBAQAAAAAAAAAAAAADBQQGBwgCAQEBAAMBAQAAAAAAAAAAAAAAAAECAwQFEAACAQIEAgUHCAYIBQUAAAABAgMABBESBQYhEzFBIhQHUWHSkzRVFnEysiNzdLQVgZGhQmKC4VJyM1OzNgjBomMkF7FDoyUmEQEAAgIAAwgBBAICAgMAAAAAAQIRAyExEvBBUWFxMhMEIoHB0eGRsaFCghSSIwX/2gAMAwEAAhEDEQA/APROgaBoUuhadLLp1q8j2sLO7QxlmYxqSSSvEmgz/hzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NA+HNve67T1EXo0D4c297rtPURejQPhzb3uu09RF6NBgaloGhJeaUqadaqsl0yyAQxgMvdZ2wPZ4jMoNBn7c/09pf3SD/ACloLGgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgrtZ3Do+jLbNqdwLcXcot7bFXYyTMMVjUIGxZs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";
		// create a buffered image
		File newFile = null; 
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			newFile = new File(storageDirectory + File.separator+ document.getTitle());
			imageByte = decoder.decodeBuffer(document.getFileContents());
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
	
			// write the image to a file
			//File outputfile = new File("D:/vijay/OCR-POC/restapi/RESTfulExample/image.jpg");
			ImageIO.write(image, "jpg", newFile);		
		} catch (Exception ex) {
			ex.printStackTrace();;
			System.out.println();
		}
		return newFile;
	}

	@Override
	public PersonalInfo parseBusinessCard(String textContents) {
		return fusionResumeParser.parseResume(textContents);
	}
}
