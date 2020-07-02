package com.inzent.medialibrary.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.ContentVO;

@Repository
public class ContentDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<ContentVO> getContentList(Long folderId) {
		return sqlSession.selectList("getcontentlistbyfolderid",folderId);
	}
}
