package com.inzent.medialibrary.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddConAlbumDTO {
	private List<Long> ids;
	private Long album_id;
}
