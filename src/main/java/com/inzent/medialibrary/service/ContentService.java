package com.inzent.medialibrary.service;

import java.util.List;

import com.inzent.medialibrary.dto.ContentVO;

public interface ContentService {
	public List<ContentVO> getContentList(Long folderId);
}
