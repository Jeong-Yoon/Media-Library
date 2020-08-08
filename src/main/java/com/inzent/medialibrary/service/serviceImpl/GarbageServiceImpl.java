package com.inzent.medialibrary.service.serviceImpl;

import java.awt.Image;
import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.DeleteDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.repository.GarbageDAO;
import com.inzent.medialibrary.service.GarbageService;

@Service
public class GarbageServiceImpl implements GarbageService{
	@Autowired
	private GarbageDAO garbageDAO;

	@Override
	public List<String> getGarbageList(String userEmail) {
		return garbageDAO.getGarbageList(userEmail);
	}

	@Override
	public int restoreContent(List<Long> contentIdList) {
		return garbageDAO.restoreContent(contentIdList);
	}

	@Override
	public int hardDelete(DeleteDTO deleteDTO) {
//		public int hardDelete(List<Long> contentIdList) {
		List<ImageDTO> list = garbageDAO.getDeleteList(deleteDTO.getIds());
		long sum = 0;
		for (ImageDTO im : list) {
			String path = im.getContent_storage();
			File deleteFile = new File(path);
			deleteFile.delete();
			sum += im.getContent_size();
		}
		return garbageDAO.hardDelete(deleteDTO.getIds(), list, sum, deleteDTO.getEmail());
//		return garbageDAO.hardDelete(contentIdList);
//		return list.size();
	}
}
