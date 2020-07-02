package com.inzent.medialibrary.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderUserDTO;
import com.inzent.medialibrary.dto.FolderVO;
import com.inzent.medialibrary.repository.FolderDAO;
import com.inzent.medialibrary.service.FolderService;

@Service
public class FolderServiceImpl implements FolderService{

	@Autowired
	private FolderDAO folderDAO;

	@Override
	public Long addFolder(AddFolderDTO addFolderDTO) {
		return folderDAO.addFolder(addFolderDTO);
	}

	@Override
	public int addFolderUser(FolderUserDTO folderUserDTO) {
		return folderDAO.addFolderUser(folderUserDTO);
	}

	@Override
	public List<FolderVO> getFolderList(Long id) {
		return folderDAO.getFolderList(id);
	}

	@Override
	public int changeFolderName(FolderNameDTO folderNameDTO) {
		return folderDAO.changeFolderName(folderNameDTO);
	}
}
