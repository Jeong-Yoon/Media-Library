package com.inzent.medialibrary.controller.api;

import java.net.MalformedURLException;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/videos")
public class VideoController {
	
	
    @GetMapping("/videos/{name}/full")
    public ResponseEntity<UrlResource> getFullVideo(@PathVariable String name) throws MalformedURLException {
        UrlResource video = new UrlResource("file:${video.location}/${name}");
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }

	
}
