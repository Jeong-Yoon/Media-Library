package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFolderDTO {
	private String type;
	private String name;
	private String path;
	private Long parent;
	private Long reg_user;
	private Long update_user;
}
