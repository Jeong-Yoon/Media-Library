package com.inzent.medialibrary.service;

import java.util.List;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderUserDTO;
import com.inzent.medialibrary.dto.FolderVO;

public interface FolderService {
	public Long addFolder(AddFolderDTO addFolderDTO);

	public int addFolderUser(FolderUserDTO folderUserDTO);

	public List<String> getFolderList(Long id);

	public int changeFolderName(FolderNameDTO folderNameDTO);

	public Long getfolderIdByUserId(Long user_id);

	public Long createRootFolder(Long user_id);
}
