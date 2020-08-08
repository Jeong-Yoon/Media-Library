package com.inzent.medialibrary.service;

import java.util.List;

import com.inzent.medialibrary.dto.DeleteDTO;

public interface GarbageService {

	List<String> getGarbageList(String userEmail);

	int restoreContent(List<Long> contentIdList);

	int hardDelete(DeleteDTO deleteDTO);
//	int hardDelete(List<Long> contentIdList);

}
