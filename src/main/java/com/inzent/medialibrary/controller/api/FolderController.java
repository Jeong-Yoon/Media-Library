package com.inzent.medialibrary.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.ParentIdDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.service.FolderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/folders")
public class FolderController {
	
	@Autowired
	private FolderService folderService;
	@Autowired
	private ContentService contentService;
	
	@PostMapping("/add")
	public ResponseEntity<Integer> addFolder(@RequestBody @Valid AddFolderDTO addFolderDTO, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		int result = folderService.addFolder(addFolderDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/getfolders/{parent}")
	public ResponseEntity<List<Map<String, Object>>> getFolderList(@PathVariable(value="parent") Long parent) throws JsonMappingException, JsonProcessingException{
		System.out.println("get folder" + parent);
		List<String> list = folderService.getFolderList(parent);
		List<Map<String, Object>> folderlist = new ArrayList<Map<String,Object>>();
		for (String s : list) {
			Map<String, Object> map = new ObjectMapper().readValue(s, HashMap.class);
			if (map.get("id").toString().startsWith("3")) {
				if (map.get("content_type").toString().equals("I")) {
					map.put("content", contentService.getContentById(Long.parseLong(map.get("id").toString())).getContent());
				} 
			}
			folderlist.add(map);
		}
		return new ResponseEntity<List<Map<String, Object>>> (folderlist, HttpStatus.OK);
	}  
	
	@PutMapping
	public ResponseEntity<?> changeFolderName(@RequestBody FolderNameDTO folderNameDTO){
		folderService.changeFolderName(folderNameDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/bytarget")
	public ResponseEntity<?> selectTarget(@RequestBody SelectTargetDTO selectTargetDTO){
		System.out.println("target");
		List<?> list = new ArrayList<>();
		if (selectTargetDTO.getTarget().equals("folder")) {
			list = folderService.selectTarget(selectTargetDTO);
		} else if(selectTargetDTO.getTarget().equals("image") || selectTargetDTO.getTarget().equals("video")) {
			list = contentService.selectTarget(selectTargetDTO);
		}
		System.out.println(list.get(0));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/{folder_id}")
	public ResponseEntity<?> deleteFolder(@PathVariable("folder_id") Long folderId){
		return new ResponseEntity<>(HttpStatus.OK);
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
