package com.inzent.medialibrary.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jcodec.api.JCodecException;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.FolderIdDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.ImageListDTO;
import com.inzent.medialibrary.dto.ParentIdDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;
import com.inzent.medialibrary.dto.VideoListDTO;

public interface ContentService {
	public List<ContentVO> getImageList(Long folderId);
	public int uploadContent(ContentDTO contentDTO);
	public void uploadContentDetail(ContentDetailDTO detailDTO, Map<String, Object> json);
	public void getContentDetail(Long contentId);
	public ImageDTO getContentById(Long image_id);
	public int deleteContent(List<Long> ids);
	public List<ImageDTO> selectTarget(SelectTargetDTO selectTargetDTO);
	public List<ImageDTO> getImageList(FolderIdDTO folderIdDTO);
	public List<ImageDTO> getVideoList(VideoListDTO videoListDTO) throws IOException, JCodecException;
}
