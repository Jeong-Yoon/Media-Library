package com.inzent.medialibrary.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UploadContentDTO {
	private String content_type;
	private String content_origin_name;
	private String content_save_name;
	private String content_storage;
	private Long content_size;
	private String content_reg_date;
	private long user_id;
	private Long folder_id;
	private Map<String, Object> content_attribute;
}
