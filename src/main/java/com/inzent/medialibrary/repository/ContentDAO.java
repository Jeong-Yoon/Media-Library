package com.inzent.medialibrary.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.UploadContentDTO;

@Repository
public class ContentDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<ContentVO> getContentList(Long folderId) {
		return sqlSession.selectList("getcontentlistbyfolderid",folderId);
	}

	public long uploadContent(UploadContentDTO ucDTO) {
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
		return sqlSession.selectOne("getcontentbyid",imageId);
	}
}
