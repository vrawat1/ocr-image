package com.fusion.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.fusion.ocr.rest.ImageDocument;
import com.fusion.parser.PersonalInfo;

public interface OcrService {
	public String processImage(String file);
	public String saveImage(MultipartFile file);
	public File saveImage(ImageDocument document);
	public PersonalInfo parseBusinessCard(String textContents);
}
