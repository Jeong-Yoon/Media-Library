package com.inzent.medialibrary.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentIdDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.service.ContentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contents")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@GetMapping("/{folder_id}")
	public ResponseEntity<List<ContentVO>> getContentList(@PathVariable("folder_id")Long folderId){
		List<ContentVO> fileList = contentService.getContentList(folderId);
		return new ResponseEntity<>(fileList, HttpStatus.OK);	
	}
	
	
	@PostMapping("/upload")
//	public FileUploadResponse uploadContent(@RequestParam("file") MultipartFile file){
	public ResponseEntity<?> uploadContent(ContentDTO contentDTO){
//		ContentDTO cone = new ContentDTO();
//		cone.setContent(file);
//		contentService.uploadContent(cone);
		System.out.println("upload test=============================");
		System.out.println(contentDTO.toString());
		System.out.println(contentDTO.getAttachFiles()[0].getContentType());
		System.out.println(contentDTO.getAttachFiles()[0].getOriginalFilename());
		System.out.println(contentDTO.getAttachFiles()[0].getName());
		System.out.println(contentDTO.getAttachFiles()[0].getSize());
		System.out.println(contentDTO.getAttachFiles()[0].getResource());
		System.out.println(contentDTO.getAttachFiles()[0].getClass());
		contentService.uploadContent(contentDTO);
//		System.out.println(contentDTO.getClass());
//		System.out.println(contentDTO.getRegUser());
//		System.out.println(contentDTO.getAttachFiles().getContentType());
//		System.out.println(contentDTO.getAttachFiles().getOriginalFilename());
//		System.out.println(contentDTO.getFolder());
//		System.out.println(contentDTO.getRegUser());
		
		//TODO
//		Map<String, Object> json = detailDTO.getContent_attribute();
//		contentService.uploadContentDetail(detailDTO, json);
//		contentService.uploadContent(contentDTO);
		
		
//		System.out.println(contentDTO.getContent().getContentType());
//		System.out.println(contentDTO.getContentType());
//        return new FileUploadResponse(file.getName(), file.getContentType(), file.getSize());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/upload2")
	public void FileUploadResponse(@RequestBody ContentDetailDTO detailDTO) {
		System.out.println("@222222222222");
		System.out.println(detailDTO.getContent_id());
		System.out.println(detailDTO.getContent_attribute());
		Map<String, Object> json = detailDTO.getContent_attribute();
		contentService.uploadContentDetail(detailDTO, json);
	}
	
	@GetMapping("/content/{content_id}")
	public void getContentDetail(@PathVariable("content_id")Long contentId) {
		contentService.getContentDetail(contentId);
	}
	
	@DeleteMapping()
	public void deleteContent(@RequestBody ContentIdDTO contentIdDTO) {
		System.out.println("content delete");
		contentService.deleteContent(contentIdDTO.getContent_id());
	}
	
}

