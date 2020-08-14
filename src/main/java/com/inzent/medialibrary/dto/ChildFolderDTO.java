package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildFolderDTO {
	private Long folder_id;
	private String folder_name;
	private Long folder_parent;
}
