package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadContentDTO {
	private String filename;
	private String content_type;
	private String content_origin_name;
	private String content_save_name;
	private String content_storage;
	private int content_size;
	private String content_reg_date;
	private String content_delete;
	private Long content_reg_user;
	private Long folder_id;
}
