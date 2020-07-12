package com.inzent.medialibrary.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDTO {
	private Long regUser;
	private Long folder;
	private MultipartFile content;
}
