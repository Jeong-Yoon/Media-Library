package com.inzent.medialibrary.service;

import java.util.List;
import java.util.Map;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;

public interface ContentService {
	public List<ContentVO> getContentList(Long folderId);
	public int uploadContent(ContentDTO contentDTO);
	public void uploadContentDetail(ContentDetailDTO detailDTO, Map<String, Object> json);
	public void getContentDetail(Long contentId);
}
