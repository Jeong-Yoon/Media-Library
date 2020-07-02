package com.inzent.medialibrary.service;

import java.util.List;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderUserDTO;
import com.inzent.medialibrary.dto.FolderVO;

public interface FolderService {
	public Long addFolder(AddFolderDTO addFolderDTO);
	public int addFolderUser(FolderUserDTO folderUserDTO);
	public List<FolderVO> getFolderList(Long id);
	public int changeFolderName(FolderNameDTO folderNameDTO);
}
