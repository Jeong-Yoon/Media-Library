package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolderVO {
	private Long folder_id;
	private String folder_type; // O/S
	private String folder_name;
	private String folder_path;
	private String folder_reg_date;
	private String folder_update_date;
	private String folder_delete;
	private Long folder_parent;
	private Long folder_reg_user;
	private Long folder_update_user;
}
