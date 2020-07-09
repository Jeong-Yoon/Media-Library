package com.inzent.medialibrary.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.medialibrary.dto.EmailDTO;
import com.inzent.medialibrary.dto.FolderUserDTO;
import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.SignUpDTO;
import com.inzent.medialibrary.dto.UserVO;
import com.inzent.medialibrary.security.JwtService;
import com.inzent.medialibrary.service.FolderService;
import com.inzent.medialibrary.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private FolderService folderService;
	

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		UserVO user = new UserVO();
		user = userService.login(loginDTO);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Long folder_id = folderService.getfolderIdByUserId(user.getUser_id());
		Map<String, Object> map = new HashMap<String, Object>();
		String token = jwtService.getToken(user);
		map.put("accessToken", token);
		map.put("root_folder", folder_id);
		return token != null ? new ResponseEntity<Object>(map, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/signup")
	public ResponseEntity<Integer> signup(@RequestBody @Valid SignUpDTO signUpDTO, BindingResult bindingResult) {
		// 폼을 만족하지 못한 경우 (400 : BAD_REQUEST)
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// 비밀번호과 비밀번호 확인이 다른 경우 (400 : BAD_REQUEST)
		if (!signUpDTO.getPassword().equals(signUpDTO.getPassword2())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// 이미 존재하는 이메일인 경우 (409 : CONFLICT 요청 충돌)
		if (userService.emailChk(signUpDTO.getEmail()) == 1) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		Long user_id = userService.signup(signUpDTO);
		Long folder_id = folderService.createRootFolder(user_id);
		FolderUserDTO folderUserDTO = new FolderUserDTO();
		folderUserDTO.setFolder_id(folder_id);
		folderUserDTO.setUser_id(user_id);
		int result = folderService.addFolderUser(folderUserDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PostMapping("/emailCheck")
	public ResponseEntity<Integer> emailCheck(@RequestBody EmailDTO emailDto) {
		int result = userService.emailChk(emailDto.getEmail());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
