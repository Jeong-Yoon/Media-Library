package com.inzent.medialibrary.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFolderDTO {
	private String type;
	@NotNull
	private String name;
	private String path;
	@NotNull
	private Long parent;
	@NotNull
	private Long reg_user;
	private Long update_user;
}
