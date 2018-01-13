/**
 * 
 */
package com.fusion.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vijrawat
 *
 */
@Component
public class FusionResumeParser {
	
	@Autowired
	private NameExtractor nameExtractor;
	
	@Autowired
	private EmailExtractor emailExtractor;
	
	@Autowired
	private PhoneNumberExtractor phoneNumberExtractor;
	
	@Autowired
	private SocialLinksExtractor socialLinksExtractor; 
	
	@Autowired
	private JobTitleExtractor jobTitleExtractor;
	
	public PersonalInfo parseResume(String resumeText) {
		PersonalInfo personalInfo = new PersonalInfo();
		try (BufferedReader reader = new BufferedReader(new StringReader(resumeText))) {
			String line = null;
			while ((line=reader.readLine()) != null) {
				setFullName(personalInfo, line);
				setEmail(personalInfo, line);
				setPhone(personalInfo, line);
				setSocialLink(personalInfo, line);	
				setJobTitle(personalInfo, line);
			}
		} catch (IOException exc) {
			// quit
		}
		return personalInfo;
	}

	private void setSocialLink(PersonalInfo personalInfo, String line) {
		String socialLink = socialLinksExtractor.extract(line);
		if (socialLink != null) {
			personalInfo.addSocialLink(socialLink);
		}
	}
	
	private void setJobTitle(PersonalInfo personalInfo, String line) {
		String jobTitle = jobTitleExtractor.extract(line);
		if (jobTitle != null) {
			personalInfo.setJobTitle(jobTitle);
		}
	}

	private void setPhone(PersonalInfo personalInfo, String line) {
		if (personalInfo.getPhone() == null) {
			String phone = phoneNumberExtractor.extractPhoneNumber(line);
			if (phone != null)
				personalInfo.setPhone(phone);
		}	
	}

	private void setEmail(PersonalInfo personalInfo, String line) {
		if (personalInfo.getEmail() == null) {
			String email = emailExtractor.extract(line);
			if (email != null) 
				personalInfo.setEmail(email);
		}	
	}

	private void setFullName(PersonalInfo personalInfo, String line) {
		if (personalInfo.getFullName() == null) {
			FullName fullName = nameExtractor.extractName(line);
			if (fullName != null) {
				personalInfo.setFullName(fullName);			
			}
		}	
	}
}
