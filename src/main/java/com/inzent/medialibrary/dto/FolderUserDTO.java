package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolderUserDTO {
	private Long user_id;
	private Long folder_id;
	private String master_type;
}
