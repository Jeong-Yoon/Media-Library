package com.inzent.medialibrary.controller.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.ImageIdDTO;
import com.inzent.medialibrary.repository.ContentDAO;
import com.inzent.medialibrary.service.ContentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/images")
public class ImageController {
	@Autowired
	ContentService contentService;
	
	@PostMapping("/getimages")
	public ResponseEntity<byte[]> getImage(@RequestBody ImageIdDTO imageIdDTO) throws IOException{
		System.out.println(imageIdDTO.getImage_id());
		ImageDTO image = contentService.getContentById(imageIdDTO.getImage_id());
		System.out.println("image"+image.getContent_storage());
//		String path = image.getContent_storage();
//		Path localPath = Paths.get(path);
//		Files.read
//		byte[] imageBytes = Files.readAllBytes(localPath);
		InputStream in = new FileInputStream(image.getContent_storage());
		image.setContent(IOUtils.toByteArray(in));
		image.setContent_type("image/jpeg");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.add("Content-Disposition", "attachment; filename=\"" + new String(image.getContent_origin_name().getBytes("UTF-8"), "ISO-8859-1")+"\""); 
		return new ResponseEntity<byte[]>(image.getContent(),headers, HttpStatus.OK);
//		return new ResponseEntity<ImageDTO>(image, HttpStatus.OK);
	}
	
//    @GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
//         // Load file as Resource
//        Resource resource = service.loadFileAsReso urce(fileName);
// 
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
// 
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
// 
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
}
