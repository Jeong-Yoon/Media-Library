package com.inzent.medialibrary.controller.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.ImageIdDTO;
import com.inzent.medialibrary.service.ContentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/images")
public class ImageController {
	@Autowired
	ContentService contentService;
	
	@PostMapping("/getimages")
	public ResponseEntity<ImageDTO> getImage(@RequestBody ImageIdDTO imageIdDTO) throws IOException{
		System.out.println(imageIdDTO.getImage_id());
		ImageDTO image = contentService.getContentById(imageIdDTO.getImage_id());
		System.out.println("image"+image.getContent_storage());
//		String path = image.getContent_storage();
//		Path localPath = Paths.get(path);
//		Files.read
//		byte[] imageBytes = Files.readAllBytes(localPath);
		InputStream in = new FileInputStream(image.getContent_storage());
		image.setContent(IOUtils.toByteArray(in));
//		image.setContent_type("image/jpeg");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.add("Content-Disposition", "attachment; filename=\"" + new String(image.getContent_origin_name().getBytes("UTF-8"), "ISO-8859-1")+"\""); 
//		return new ResponseEntity<byte[]>(image.getContent(),headers, HttpStatus.OK);
		return new ResponseEntity<ImageDTO>(image, HttpStatus.OK);
	}
	
}
