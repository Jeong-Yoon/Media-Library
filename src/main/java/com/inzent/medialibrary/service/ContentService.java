package com.inzent.medialibrary.service;

import java.util.List;
import java.util.Map;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.FolderIdDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.ImageListDTO;
import com.inzent.medialibrary.dto.ParentIdDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;

public interface ContentService {
	public List<ContentVO> getContentList(Long folderId);
	public int uploadContent(ContentDTO contentDTO);
	public void uploadContentDetail(ContentDetailDTO detailDTO, Map<String, Object> json);
	public void getContentDetail(Long contentId);
	public ImageDTO getContentById(long image_id);
	public int deleteContent(List<Long> contentIdList);
	public List<ImageDTO> selectTarget(SelectTargetDTO selectTargetDTO);
	public List<ImageDTO> getImageList(FolderIdDTO folderIdDTO);
}
