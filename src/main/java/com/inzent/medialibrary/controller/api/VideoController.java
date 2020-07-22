package com.inzent.medialibrary.controller.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
import com.inzent.medialibrary.dto.ImageDTO;
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
//        log.info("getVideo");
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

}
