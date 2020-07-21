package com.inzent.medialibrary.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderVO;

@Repository
public class FolderDAO {

	@Autowired
	private SqlSession sqlSession;

	public int addFolder(AddFolderDTO addFolderDTO) {
		return sqlSession.insert("addfolder", addFolderDTO);
	}
	
	public List<String> getFolderList(Long id) {
		List<String> list = sqlSession.selectList("getchildren", id);
		for(String s : list) {
			System.out.println(s);
		}
		return list;
//		return sqlSession.selectList("getfolderlistbyparentid", id);
	}

	public int changeFolderName(FolderNameDTO folderNameDTO) {
		return sqlSession.update("changefoldername", folderNameDTO);
	}

	public Long getfolderIdByUserId(Long user_id) {
		return sqlSession.selectOne("getfolderidbyuserid",user_id);
	}

	public long createRootFolder(Long user_id) {
		return sqlSession.insert("createrootfolder",user_id);
	}

	public List<FolderVO> selectTarget(Long id) {
		return sqlSession.selectList("getfolderlistbyparentid", id);
	}
}
