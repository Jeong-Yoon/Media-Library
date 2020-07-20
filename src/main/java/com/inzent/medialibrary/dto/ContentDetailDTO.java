package com.inzent.medialibrary.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentDetailDTO {
	private long content_id;
	private Map<String, Object> content_attribute;
}
