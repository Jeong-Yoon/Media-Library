package com.inzent.medialibrary.controller.api;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.AddAlbumDTO;
import com.inzent.medialibrary.dto.AddConAlbumDTO;
import com.inzent.medialibrary.dto.AlbumVO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.service.AlbumService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/albums")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/{email}")
	public ResponseEntity<List<AlbumVO>> getAlbumList(@PathVariable(value="email")String email){
		List<AlbumVO> list = albumService.getAlbumList(email);
		return new ResponseEntity<List<AlbumVO>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/albumnames/{email}")
	public ResponseEntity<List<String>> getAlbumNameList(@PathVariable(value="email")String email){
		List<String> list = albumService.getAlbumNameList(email);
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/intoAlbum/{album_id}")
	public ResponseEntity<List<ImageDTO>> getContentInAlbum(@PathVariable(value="album_id") Long album_id) throws IOException, JCodecException{
		List<ImageDTO> list = albumService.getContentInAlbum(album_id);
		return new ResponseEntity<List<ImageDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Integer> addAlbum(@RequestBody AddAlbumDTO addAlbumDTO){
		System.out.println("album controller add album");
		int result = albumService.addAlbum(addAlbumDTO);
		if(result != 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<Integer> deleteAlbums(@RequestBody List<Long> albumIds){
		int result = albumService.deleteAlbums(albumIds);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteitem")
	public ResponseEntity<Integer> deleteContents(@RequestBody AddConAlbumDTO addConAlbumDTO){
		int result = albumService.deleteContents(addConAlbumDTO);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PostMapping("/additem")
	public ResponseEntity<Integer> addContent(@RequestBody AddConAlbumDTO addConAlbumDTO){
		int result = albumService.addContent(addConAlbumDTO);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
 }
