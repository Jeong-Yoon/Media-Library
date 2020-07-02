package com.inzent.medialibrary.controller.api;

import java.util.List;

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

import com.inzent.medialibrary.dto.AddFolderDTO;
import com.inzent.medialibrary.dto.FolderNameDTO;
import com.inzent.medialibrary.dto.FolderUserDTO;
import com.inzent.medialibrary.dto.FolderVO;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.service.FolderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/folders")
public class FolderController {
	
	@Autowired
	private FolderService folderService;
	
	@PostMapping("/add")
	public ResponseEntity<Integer> addFolder(@RequestBody @Valid AddFolderDTO addFolderDTO, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		long folder_id = folderService.addFolder(addFolderDTO);
		FolderUserDTO folderUserDTO = new FolderUserDTO();
		folderUserDTO.setUser_id(addFolderDTO.getReg_user());
		folderUserDTO.setFolder_id(folder_id);
		int result = folderService.addFolderUser(folderUserDTO);
		//folders_users_info 채워넣기
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/{folder_id}")
	public ResponseEntity<List<FolderVO>> getFolderList(@PathVariable("folder_id") Long id){
		List<FolderVO> folderlist = folderService.getFolderList(id);
		return new ResponseEntity<List<FolderVO>>(folderlist, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> changeFolderName(@RequestBody FolderNameDTO folderNameDTO){
		folderService.changeFolderName(folderNameDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{folder_id}")
	public ResponseEntity<?> deleteFolder(@PathVariable("folder_id") Long folderId){
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
