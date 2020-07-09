package com.inzent.medialibrary.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.SignUpDTO;
import com.inzent.medialibrary.dto.UserVO;
import com.inzent.medialibrary.repository.FolderDAO;
import com.inzent.medialibrary.repository.UserDAO;
import com.inzent.medialibrary.security.JwtService;
import com.inzent.medialibrary.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserVO login(LoginDTO loginDto) {
		UserVO user = userDao.login(loginDto);
		if(!passwordEncoder.matches(loginDto.getPassword(), user.getUser_pw())) {
			return null;
		}
		return user;
	}

	@Override
	@Transactional
	public Long signup(SignUpDTO signUpDTO) {
		String encodePw = passwordEncoder.encode(signUpDTO.getPassword());
		signUpDTO.setPassword(encodePw);
		return userDao.signup(signUpDTO);
	}

	@Override
	public int emailChk(String email) {
		return userDao.emailChk(email);
	}
	
	@Override
	public long getUserIdByEmail(String email) {
		return userDao.getUserIdByEmail(email);
	}
}
