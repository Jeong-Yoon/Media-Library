package com.inzent.medialibrary.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderUserDTO;
import com.inzent.medialibrary.dto.FolderVO;

@Repository
public class FolderDAO {

	@Autowired
	private SqlSession sqlSession;

	public long addFolder(AddFolderDTO addFolderDTO) {
		return sqlSession.insert("addfolder", addFolderDTO);
	}
	
	public int addFolderUser(FolderUserDTO folderUserDTO) {
		int chk = sqlSession.selectOne("chkfolderid", folderUserDTO.getFolder_id());
		if(chk == 1) {
			folderUserDTO.setMaster_type("N");
		} else {
			folderUserDTO.setMaster_type("Y");
		}
		return sqlSession.insert("addfolderuser", folderUserDTO);
	}

	public List<FolderVO> getFolderList(Long id) {
		return sqlSession.selectList("getfolderlistbyparentid", id);
	}

	public int changeFolderName(FolderNameDTO folderNameDTO) {
		return sqlSession.update("changefoldername", folderNameDTO);
	}
}
