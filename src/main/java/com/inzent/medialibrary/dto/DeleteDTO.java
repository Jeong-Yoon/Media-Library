package com.inzent.medialibrary.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteDTO {
	private List<Long> ids;
	private String email;
}
