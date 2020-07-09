package com.inzent.medialibrary.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFolderDTO {
	private Long folder_id;
	@NotNull
	private String newFolderName;
	private String path;
	@NotNull
	private Long parent;
	@NotNull
	private String userEmail;
	private Long reg_user;
	private Long update_user;
}
