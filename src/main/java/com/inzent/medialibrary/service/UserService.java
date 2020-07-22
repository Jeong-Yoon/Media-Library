package com.inzent.medialibrary.service;

import com.inzent.medialibrary.dto.CapacityDTO;
import com.inzent.medialibrary.dto.EmailDTO;
import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.LoginUserDTO;
import com.inzent.medialibrary.dto.SignUpDTO;

public interface UserService {
	public LoginUserDTO login(LoginDTO loginDto);
	public int signup(SignUpDTO signUpDTO);
	public int emailChk(String email);
	public long getUserIdByEmail(String email);
	public CapacityDTO getCapacity(EmailDTO emailDTO);
}
