package com.fusion.domain;

import java.io.Serializable;

import com.fusion.parser.PersonalInfo;

public class UploadedFile implements Serializable {

	private static final long serialVersionUID = -38331060124340967L;
	private String name;
	private Integer size;
	private String url;
	private String thumbnail_url;
	private String textContents;
	private PersonalInfo personalInfo;
	
	public UploadedFile() {
		super();
	}
	
	public UploadedFile(String name, Integer size, String url) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
	}
	
	public UploadedFile(String name, Integer size, String url,
			String thumbnail_url, String delete_url, String delete_type) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.thumbnail_url = thumbnail_url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
		
	public String getTextContents() {
		return textContents;
	}

	public void setTextContents(String textContents) {
		this.textContents = textContents;
	}
	
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadedFile [name=");
		builder.append(name);
		builder.append(", size=");
		builder.append(size);
		builder.append(", url=");
		builder.append(url);
		builder.append(", thumbnail_url=");
		builder.append(thumbnail_url);
		builder.append(", delete_url=");
		builder.append(", textContents=");
		builder.append(textContents);
		builder.append(", personalInfo=");
		builder.append(personalInfo);
		builder.append("]");
		return builder.toString();
	}
	
}
