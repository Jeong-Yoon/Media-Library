package com.inzent.medialibrary.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.service.ShareService;
import com.inzent.medialibrary.utils.GetThumbnail;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shares")
public class ShareController {
	
	@Autowired
	private ShareService shareService;
	@Autowired
	private ContentService contentService;
	
	@GetMapping("/getshareitems")
	public ResponseEntity<List<Map<String, Object>>> getShareItems() throws IOException, JCodecException{
		System.out.println("get share items");
		List<String> list = shareService.getShareItems();
		List<Map<String, Object>> itemList = new ArrayList<Map<String,Object>>();
		for (String s : list) {
			Map<String, Object> map = new ObjectMapper().readValue(s, HashMap.class);
			if (map.get("id").toString().startsWith("3")) {
				if (map.get("content_type").toString().equals("I")) {
					map.put("content", contentService.getContentById(Long.parseLong(map.get("id").toString())).getContent());
				} else if(map.get("content_type").toString().equals("V")) {
					File file = new File(contentService.getContentById(Long.parseLong(map.get("id").toString())).getContent_storage());
					map.put("content",GetThumbnail.getThumbnail(file));
				}
			}
			itemList.add(map);
		}
		return new ResponseEntity<List<Map<String, Object>>> (itemList, HttpStatus.OK);
	}
	
	@PutMapping("/unshare")
	public ResponseEntity<Integer> unshareItems(@RequestBody List<Long> ids){
		int result = shareService.unshareItems(ids);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PutMapping("/share")
	public ResponseEntity<Integer> shareItems(@RequestBody List<Long> ids){
		int result = shareService.shareItems(ids);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
