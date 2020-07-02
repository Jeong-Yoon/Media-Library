package com.inzent.medialibrary.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.repository.ContentDAO;
import com.inzent.medialibrary.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentDAO contentDAO;

	@Override
	public List<ContentVO> getContentList(Long folderId) {
		return contentDAO.getContentList(folderId);
	}
}
