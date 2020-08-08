package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumVO {
	private Long album_id;
	private String album_name;
	private String album_reg_date;
	private Long album_reg_user;
}
