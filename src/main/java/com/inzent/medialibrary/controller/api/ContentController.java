package com.inzent.medialibrary.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.READER;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentIdDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.DownloadIdDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.service.ContentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contents")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@GetMapping("/{folder_id}")
	public ResponseEntity<List<ContentVO>> getImageList(@PathVariable("folder_id")Long folderId){
		List<ContentVO> fileList = contentService.getImageList(folderId);
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
	
//	@PostMapping("/download")
//	public ResponseEntity<Resource> downloadFile(@RequestBody DownloadIdDTO downloadIdDTO){
//		System.out.println("contents download");
//		System.out.println(downloadIdDTO.getId());
//		ImageDTO content = contentService.getContentById(downloadIdDTO.getId());
//		String filePath = content.getContent_storage();
//		File target = new File(filePath);
//		HttpHeaders header = new HttpHeaders();
//		Resource rs = null;
//		if (target.exists()) {
//			try {
//				String mimeType = Files.probeContentType(Paths.get(target.getAbsolutePath()));
//				if (mimeType == null) {
//					mimeType = "octet-stream";
//				}
//				rs = new UrlResource(target.toURI());
//				header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rs.getFilename() + "\"");
//				header.setCacheControl("no-cache");
//				header.setContentType(MediaType.parseMediaType(mimeType));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return new ResponseEntity<Resource>(rs, header, HttpStatus.OK);
////		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	
//	 @PostMapping("/download")
//	    public ResponseEntity<?> downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> downloadIdDTO) {
////	         System.out.println("파일 다운");
////	        // System.out.println(f.toString());
//		 	ImageDTO f = contentService.getContentById(downloadIdDTO.get(0));
////	        try {
////	            File file = new File(f.getContent_storage());
////	            Files.copy(file.toPath(), response.getOutputStream());
////	            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
////	            String contentDisposition = String.format("attachment; filename=%s", f.getContent_origin_name());
////	            int fileSize = Long.valueOf(file.length()).intValue();
////	            System.out.println(file.getName() + "==========================");
////	            System.out.println(mimeType + " mimeType");
////	            System.out.println(fileSize);
////	            response.setContentType(mimeType);
////	            response.setHeader("Content-Disposition", contentDisposition);
////	            response.setHeader("filename", file.getName());
////	            response.setContentLength(fileSize);
//////				InputStream in = new FileInputStream(f.getContent_storage());
////
//////	            response.setContentType("application/octet-stream"); 
//////	        	response.setContentLength(fileSize); 
//////	        	response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(f.getContent_origin_name(), "UTF-8") + ";");
//////	        	response.setHeader("Content-Transfer-Encoding", "binary"); 
//////	        	response.setHeader("Content-Type", "application/octet-stream");
//////	        	response.setContentType("application/octet-stream");
//////	        	response.setHeader("filename", URLEncoder.encode(f.getContent_origin_name(), "UTF-8"));
//////	        	response.getOutputStream().write(IOUtils.toByteArray(in)); 
//////	        	System.out.println(response.toString());
//////	        	System.out.println(response.getOutputStream());
//////	        	response.getOutputStream().flush(); 
//////	        	response.getOutputStream().close();
////	            
////	            
////	        } catch (FileNotFoundException e) {
////	            // System.out.println(e);
////	        } catch (IOException e) {
////	            e.printStackTrace();
////	        }
//	        try {
//	            File file = new File(f.getContent_storage());
//	            System.out.println(file.toPath());
//	            Files.copy(file.toPath(), response.getOutputStream());
//	            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//	            String contentDisposition = String.format("attachment; filename=\"" + file.getName() + "\"");
//	            int fileSize = Long.valueOf(file.length()).intValue();
////	            response.setContentType(mimeType);
////	            response.setHeader("Content-Type", "application/octet-stream");
//	            response.addHeader("Content-Type", mimeType);
//	            response.setHeader("Content-Disposition", contentDisposition);
//	            response.setContentLength(fileSize);
//	            InputStream in = new FileInputStream(f.getContent_storage());
//	            response.getOutputStream().write(IOUtils.toByteArray(in)); 
//	            System.out.println(response.getContentType());
//	            System.out.println(response.getHeader("Content-Type"));
//	            
////	            return new ResponseEntity<>(response, header, HttpStatus.OK);
//	            
////	            return ResponseEntity.ok()
////	            		.contentType(MediaType.parseMediaType(mimeType))
////	            		.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
////	            		.body(file);
//	            
//	            HttpHeaders headers = new HttpHeaders();
//	            headers.setContentType(new MediaType("application", "octet-stream", StandardCharsets.ISO_8859_1));
//	        } catch (FileNotFoundException e) {
//	            // System.out.println(e);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	    }
	 
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/download")
	public HttpEntity<byte[]> downloadExcelReport(HttpServletRequest request, HttpServletResponse response, @RequestBody DownloadIdDTO download) throws IOException {
		System.out.println(download.getId());
		ImageDTO f = contentService.getContentById(download.getId());
		File file = new File(f.getContent_storage());
		System.out.println(file.toPath());
		Files.copy(file.toPath(), response.getOutputStream());
		InputStream in = new FileInputStream(f.getContent_storage());
		response.getOutputStream().write(IOUtils.toByteArray(in));
		byte[] content = IOUtils.toByteArray(in);
		// prepare response
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "octet-stream"));
		header.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + URLEncoder.encode(f.getContent_origin_name(), "UTF-8") + ";");
//	     header.add("Content-Disposition", "attachment; filename="+file.getName());
		header.setContentLength(content.length);

		return new HttpEntity<byte[]>(content, header);
	}
	
	// content_detail 가져가기 -> 동영상에서 사용할 듯.
	@GetMapping("/content/{content_id}")
	public void getContentDetail(@PathVariable("content_id")Long contentId) {
		contentService.getContentDetail(contentId);
	}
	
	// 아이템 삭제 -> 폴더 컨트롤러로 옮길 것.
	@DeleteMapping()
	public ResponseEntity<Integer> deleteContent(@RequestBody List<Long> ids) {
		System.out.println("content delete");
		int result = contentService.deleteContent(ids);
		if(result == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public static void thumbnail() {
		Runtime run = Runtime.getRuntime();
		String videofile = "C:/Users/Min/Desktop/test1/test.mp4";
		String command = "C:/ffmpeg-20191109-0f89a22-win64-static/bin/ffmpeg.exe -i \"" + videofile + "\" -ss 00:00:01 -vcodec png -vframes 1 \""  +videofile + "_%2d.png\""; // 동영상 1초에서 Thumbnail 추출
		System.out.println(command);
		try{
		    run.exec("cmd.exe chcp 65001");  // cmd에서 한글문제로 썸네일이 만들어지지않을시 cmd창에서 utf-8로 변환하는 명령
		    run.exec(command);
		}catch(Exception e){
		    System.out.println("error : "+e.getMessage());
		    e.printStackTrace();
		}       


	}
}

