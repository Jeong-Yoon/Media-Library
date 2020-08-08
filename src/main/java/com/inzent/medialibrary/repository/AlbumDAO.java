package com.inzent.medialibrary.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.AddAlbumDTO;
import com.inzent.medialibrary.dto.AddConAlbumDTO;
import com.inzent.medialibrary.dto.AlbumVO;
import com.inzent.medialibrary.dto.ImageDTO;

@Repository
public class AlbumDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<String> getAlbumNameList(String userEmail) {
		return sqlSession.selectList("getalbumnamelist", userEmail);
	}

	public int addAlubum(AddAlbumDTO addAlbumDTO) {
		return sqlSession.insert("addalbum", addAlbumDTO);
	}

	public int deleteAlbums(List<Long> albumIds) {
		return sqlSession.delete("deletealbums", albumIds);
	}

	public List<AlbumVO> getAlbumList(String userEmail) {
		return sqlSession.selectList("getalbumlist", userEmail);
	}

	public List<ImageDTO> getContentInAlbum(Long album_id) {
		return sqlSession.selectList("getcontentsinalbum", album_id);
	}

	public int addContent(AddConAlbumDTO addConAlbumDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("albumId", addConAlbumDTO.getAlbum_id());
		map.put("ids", addConAlbumDTO.getIds());
		return sqlSession.insert("addcontentsinalbum", map);
	}

	public int deleteContents(AddConAlbumDTO addConAlbumDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("albumId", addConAlbumDTO.getAlbum_id());
		map.put("ids", addConAlbumDTO.getIds());
		return sqlSession.insert("deletecontentsinalbum", map);
	}
	
}
