package com.inzent.medialibrary.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContentDTO {
	private String regUser;
	private Long folder;
	private MultipartFile[] attachFiles;
}
