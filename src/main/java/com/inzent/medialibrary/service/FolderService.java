package com.inzent.medialibrary.service;

import java.util.List;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderVO;
import com.inzent.medialibrary.dto.SelectTargetDTO;

public interface FolderService {
	public int addFolder(AddFolderDTO addFolderDTO);

	public List<String> getFolderList(Long id);

	public int changeFolderName(FolderNameDTO folderNameDTO);

	public Long getfolderIdByUserId(Long user_id);

	public Long createRootFolder(Long user_id);

	public List<FolderVO> selectTarget(SelectTargetDTO selectTargetDTO);

	public List<String> getShareItems();
}
