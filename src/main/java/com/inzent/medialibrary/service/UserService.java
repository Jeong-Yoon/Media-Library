package com.inzent.medialibrary.service;

import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.SignUpDTO;
import com.inzent.medialibrary.dto.UserVO;

@Service
public interface UserService {
	public UserVO login(LoginDTO loginDto);
	public int signup(SignUpDTO signUpDTO);
	public int emailChk(String email);
}
