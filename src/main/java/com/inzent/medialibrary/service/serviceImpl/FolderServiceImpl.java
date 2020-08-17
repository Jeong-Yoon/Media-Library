package com.inzent.medialibrary.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.ChildFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderVO;
import com.inzent.medialibrary.dto.SearchDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;
import com.inzent.medialibrary.repository.FolderDAO;
import com.inzent.medialibrary.repository.UserDAO;
import com.inzent.medialibrary.service.FolderService;

@Service
public class FolderServiceImpl implements FolderService{

	@Autowired
	private FolderDAO folderDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public int addFolder(AddFolderDTO addFolderDTO) {
		if(addFolderDTO.getNewFolderName().length() == 0) {
			addFolderDTO.setNewFolderName("새폴더");
		}
		Long user_id = userDAO.getUserIdByEmail(addFolderDTO.getUserEmail());
		String path = userDAO.getFolderPathById(addFolderDTO.getParent());
		addFolderDTO.setReg_user(user_id);
		addFolderDTO.setUpdate_user(user_id);
		System.out.println(path.length());
		System.out.println(path + addFolderDTO.getNewFolderName());
		if(path.length() == 1) {
			addFolderDTO.setPath(path + addFolderDTO.getNewFolderName());
		} else {
			addFolderDTO.setPath(path + "/" + addFolderDTO.getNewFolderName());			
		}
		return folderDAO.addFolder(addFolderDTO);
	}

	@Override
	public List<String> getFolderList(Long id) {
		return folderDAO.getFolderList(id);
	}

	@Override
	public int changeFolderName(FolderNameDTO folderNameDTO) {
		return folderDAO.changeFolderName(folderNameDTO);
	}

	@Override
	public Long getfolderIdByUserId(Long user_id) {
		return folderDAO.getfolderIdByUserId(user_id);
	}

	@Override
	public Long createRootFolder(Long user_id) {
		return folderDAO.createRootFolder(user_id);
	}

	@Override
	public List<FolderVO> selectTarget(SelectTargetDTO selectTargetDTO) {
		return folderDAO.selectTarget(selectTargetDTO.getParent());
	}

	@Override
	public List<ChildFolderDTO> getChild(Long parent) {
		return folderDAO.getChild(parent);
	}

	@Override
	public List<String> getSearchList(SearchDTO searchDTO) {
		return folderDAO.getSearchList(searchDTO);
	}


}
