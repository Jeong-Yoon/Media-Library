package com.inzent.medialibrary.controller.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;

import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.ContentIdDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.FolderIdDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.VideoListDTO;
import com.inzent.medialibrary.service.ContentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/videos")
public class VideoController {
	
	@Autowired
	private ContentService contentService;
	
    @PostMapping("/full")
    public ResponseEntity<UrlResource> getFullVideo(@RequestBody ContentIdDTO contentIdDTO) throws MalformedURLException {
    	ImageDTO content = contentService.getContentById(contentIdDTO.getContent_id()); 
        UrlResource video = new UrlResource("file:" + content.getContent_storage());
//    	UrlResource video = new UrlResource(content.getContent_storage());
    	System.out.println(video.getClass());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }
    
    @GetMapping("/video/{videoId}")
//    @GetMapping("/video")
    public ResponseEntity<ResourceRegion> getVideo(@PathVariable(value="videoId") Long videoId,
                                                   @RequestHeader HttpHeaders headers) throws IOException {
//    	ImageDTO content = contentService.getContentById(contentIdDTO.getContent_id()); 
    	System.out.println("video Controller");
    	System.out.println(videoId);
//    	ImageDTO content = contentService.getContentById(Long.parseLong(videoId)); 
    	ImageDTO content = contentService.getContentById(videoId); 
        UrlResource video = new UrlResource("file:" + content.getContent_storage());
        System.out.println(video.contentLength());
        System.out.println("if exists : " + video.exists());
        System.out.println(headers);
        System.out.println(headers.getRange());
        ResourceRegion region = resourceRegion(video, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                            .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                            .body(region);
    }

    private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException {
        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();
        System.out.println("region : ");
        System.out.println(contentLength);
        System.out.println(headers);
        System.out.println(headers.getRange().isEmpty());
        HttpRange httpRange;
        if(!headers.getRange().isEmpty()) {
        	httpRange = headers.getRange().stream().findFirst().get();
        } else {
        	httpRange = null;
        }
        System.out.println("httpRange " + httpRange);
        if(httpRange != null) {
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            return new ResourceRegion(video, start, rangeLength);
        } else {
            long rangeLength = Long.min(chunkSize, contentLength);
            System.out.println(rangeLength);
            System.out.println(video.contentLength());
            return new ResourceRegion(video, 0, rangeLength);
        }
    }
    
    @GetMapping("/")
    public String index(Model model, @RequestParam Long contentId) throws IOException {
    	ImageDTO content = contentService.getContentById(contentId); 
//        ClassPathResource resource = new ClassPathResource(content.getContent_storage(), getClass());
    	System.out.println(Paths.get(content.getContent_storage()).toFile());
    	Object video = Paths.get(content.getContent_storage());
//        Object[] videos = Files.list(Paths.get(content.getContent_storage()))
//                            .map(f -> f.getFileName().toString())
//                            .toArray();
        model.addAttribute("videos", video);
        return video.toString();
    }

    @GetMapping("/{videoName}")
    public String video(@PathVariable String videoName, Model model){
        model.addAttribute("videoName", videoName);
        return "video";
    }
	
    @PostMapping("/videolist")
	public ResponseEntity<List<ImageDTO>> getVideoList(@RequestBody VideoListDTO videoListDTO) throws IOException, JCodecException {
//				@RequestParam(value = "folderId", required = false) Long folderId, 
//				@RequestParam(value = "videoId", required = false) Long videoId
//			) throws IOException, JCodecException{
    		System.out.println("get video list============" + videoListDTO.getFolderId() + " , " + videoListDTO.getVideoId());
    		List<ImageDTO> list = contentService.getVideoList(videoListDTO);
    		return new ResponseEntity<List<ImageDTO>>(list, HttpStatus.OK);
    	}
    
//    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
//    @GetMapping("/video/{videoId}")
//    public void getVideo(HttpServletRequest req, HttpServletResponse res, @PathVariable Long videoId) {
//    	System.out.println("video Controller");
//    	System.out.println(videoId);
////    	ImageDTO content = contentService.getContentById(Long.parseLong(videoId)); 
//    	ImageDTO content = contentService.getContentById(videoId); 
//      String filePath = content.getContent_storage();
//      
//      // 데이터 조회
////      FileModel fileModel = fileService.getFileInfo(Integer.parseInt(id));
//      System.out.println("동영상 스트리밍 요청 : " + filePath + content.getContent_save_name());
//      File getFile = new File(filePath);
//      
//      try {
//        // 미디어 처리
//        MultipartFileSender
//          .fromFile(getFile)
//          .with(req)
//          .with(res)
//          .serveResource();
//        
//      } catch (Exception e) {
//        // 사용자 취소 Exception 은 콘솔 출력 제외
//        if (!e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException")) e.printStackTrace();
//      }
//    }



}
