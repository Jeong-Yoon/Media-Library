package com.inzent.medialibrary.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
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
	public FileUploadResponse uploadContent(@RequestParam("file") MultipartFile file){
//	public ResponseEntity<?> uploadContent(@RequestBody ContentDTO contentDTO){
		ContentDTO cone = new ContentDTO();
		cone.setContent(file);
		contentService.uploadContent(cone);
		
		//TODO
//		Map<String, Object> json = detailDTO.getContent_attribute();
//		contentService.uploadContentDetail(detailDTO, json);
//		contentService.uploadContent(contentDTO);
		
		
//		System.out.println(contentDTO.getContent().getContentType());
//		System.out.println(contentDTO.getContentType());
        return new FileUploadResponse(file.getName(), file.getContentType(), file.getSize());

//		return new ResponseEntity<>(HttpStatus.CREATED);
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
}
class FileUploadResponse {
    private String fileName;
    private String fileType;
    private long size;
    
    public FileUploadResponse(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }
 
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    public String getFileType() {
        return fileType;
    }
 
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
 
    public long getSize() {
        return size;
    }
 
    public void setSize(long size) {
        this.size = size;
    }
}

