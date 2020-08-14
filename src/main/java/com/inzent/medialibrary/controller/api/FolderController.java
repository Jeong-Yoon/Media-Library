package com.inzent.medialibrary.controller.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
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
import com.inzent.medialibrary.dto.ChildFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.ParentIdDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.service.FolderService;
import com.inzent.medialibrary.utils.GetThumbnail;

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
	public ResponseEntity<List<Map<String, Object>>> getFolderList(@PathVariable(value="parent") Long parent) throws IOException, JCodecException{
		System.out.println("get folder" + parent);
		List<String> list = folderService.getFolderList(parent);
		List<Map<String, Object>> folderlist = new ArrayList<Map<String,Object>>();
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
			folderlist.add(map);
		}
		return new ResponseEntity<List<Map<String, Object>>> (folderlist, HttpStatus.OK);
	}  
	
	@GetMapping("/getchild/{parent}")
	public ResponseEntity<List<ChildFolderDTO>> getChild(@PathVariable(value="parent") Long parent){
		List<ChildFolderDTO> result = folderService.getChild(parent);
		return new ResponseEntity<List<ChildFolderDTO>>(result, HttpStatus.OK);
		
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
	

}
