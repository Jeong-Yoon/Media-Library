package com.inzent.medialibrary.service;

import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.LoginUserDTO;
import com.inzent.medialibrary.dto.SignUpDTO;

@Service
public interface UserService {
	public LoginUserDTO login(LoginDTO loginDto);
	public int signup(SignUpDTO signUpDTO);
	public int emailChk(String email);
	public long getUserIdByEmail(String email);
}
