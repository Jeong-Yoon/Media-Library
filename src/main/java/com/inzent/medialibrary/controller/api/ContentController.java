package com.inzent.medialibrary.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.service.ContentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/files")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@GetMapping("/{folder_id}")
	public ResponseEntity<List<ContentVO>> getContentList(@PathVariable("folder_id")Long folderId){
		List<ContentVO> fileList = contentService.getContentList(folderId);
		return new ResponseEntity<>(fileList, HttpStatus.OK);	
	}
	
}
