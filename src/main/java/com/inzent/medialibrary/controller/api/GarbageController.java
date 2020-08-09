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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzent.medialibrary.dto.DeleteDTO;
import com.inzent.medialibrary.dto.EmailDTO;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.service.GarbageService;
import com.inzent.medialibrary.utils.GetThumbnail;

@RestController
@CrossOrigin
@RequestMapping("/api/garbages")
public class GarbageController {
	
	@Autowired
	private GarbageService garbageService;
	@Autowired
	private ContentService contentService;
	
	@PostMapping("/garbageList")
	public ResponseEntity<?> getGarbages(@RequestBody EmailDTO emailDTO) throws IOException, JCodecException {
		System.out.println(emailDTO.getEmail() + " garbage controller email");
		List<String> list = garbageService.getGarbageList(emailDTO.getEmail());
		List<Map<String, Object>> folderlist = new ArrayList<Map<String, Object>>();
		for (String s : list) {
			Map<String, Object> map = new ObjectMapper().readValue(s, HashMap.class);
			if (map.get("id").toString().startsWith("3")) {
				if (map.get("content_type").toString().equals("I")) {
					map.put("content",
							contentService.getContentById(Long.parseLong(map.get("id").toString())).getContent());
				} else if (map.get("content_type").toString().equals("V")) {
					File file = new File(contentService.getContentById(Long.parseLong(map.get("id").toString()))
							.getContent_storage());
					map.put("content", GetThumbnail.getThumbnail(file));
				}
			}
			folderlist.add(map);
		}
		System.out.println(list.size());
		System.out.println(folderlist.size());
		return new ResponseEntity<List<Map<String, Object>>>(folderlist, HttpStatus.OK);
	}
	
	@PostMapping("/restore")
	public ResponseEntity<Integer> restoreContent(@RequestBody List<Long> contentIdList) {
		System.out.println("content restore");
		int result = garbageService.restoreContent(contentIdList);
		if(result == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/harddelete")
	public ResponseEntity<?> hardDelete(@RequestBody DeleteDTO deleteDTO){
//		public ResponseEntity<?> hardDelete(@RequestBody List<Long> deleteDTO){
//		System.out.println(deleteDTO.getContentIdList().get(1));
//		int result = garbageService.hardDelete(deleteDTO.getContentIdList(), deleteDTO.getEmail());
		System.out.println("hard delete : " + deleteDTO.getIds().get(0) + ", " + deleteDTO.getEmail());
		int result = garbageService.hardDelete(deleteDTO);
		System.out.println("hard delete result : " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
