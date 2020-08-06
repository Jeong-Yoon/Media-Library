package com.inzent.medialibrary.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.FolderIdDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.ImageListDTO;
import com.inzent.medialibrary.dto.ParentIdDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;
import com.inzent.medialibrary.dto.UploadContentDTO;
import com.inzent.medialibrary.dto.VideoListDTO;

@Repository
public class ContentDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<ContentVO> getImageList(Long folderId) {
		return sqlSession.selectList("getimagelistbyfolderid",folderId);
	}

	public int uploadContent(UploadContentDTO ucDTO) {
		return sqlSession.insert("uploadcontent",ucDTO);
	}
	
	public int uploadContentDetail(ContentDetailDTO detailDTO, Map<String, Object> json) {
		System.out.println(detailDTO.getContent_attribute().get("re"));
		detailDTO.setContent_attribute(json);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", detailDTO.getContent_id());
		map.put("json", json);
		return sqlSession.insert("uploadcontentdetail", map);
	}
	
	public ContentDetailDTO getContentDetail(Long contentId) {
		return sqlSession.selectOne("getcontentdetail", contentId);
	}

	public ImageDTO getContentById(long imageId) {
		ImageDTO image = new ImageDTO();
//		do {
//			image = sqlSession.selectOne("getcontentbyid",imageId);
//			imageId++;
//		}while(image.getContent_type().equals("V"));
//		return image;
		return sqlSession.selectOne("getcontentbyid",imageId);
	}

	public int deleteContent(List<Long> ids) {
		System.out.println(ids.size() + " : delete dao");
		return sqlSession.update("deleteitems", ids);
	}

	public List<ImageDTO> selectTarget(SelectTargetDTO selectTargetDTO) {
		return sqlSession.selectList("selecttarget", selectTargetDTO);
	}

	public List<ImageDTO> getImageList(FolderIdDTO folderIdDTO) {
		Long folderId = folderIdDTO.getFolderId();
		return sqlSession.selectList("getimagelistbyfolderid", folderId);
	}

	public List<ImageDTO> getVideoList(VideoListDTO videoListDTO) {
		return sqlSession.selectList("getvideolistbyfolderid", videoListDTO);
	}
}
