package com.inzent.medialibrary.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inzent.medialibrary.dto.CapacityDTO;
import com.inzent.medialibrary.dto.EmailDTO;
import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.LoginUserDTO;
import com.inzent.medialibrary.dto.SignUpDTO;
import com.inzent.medialibrary.repository.UserDAO;
import com.inzent.medialibrary.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public LoginUserDTO login(LoginDTO loginDto) {
		LoginUserDTO user = userDao.login(loginDto);
		if(!passwordEncoder.matches(loginDto.getPassword(), user.getUser_pw())) {
			return null;
		}
		return user;
	}

	@Override
	@Transactional
	public int signup(SignUpDTO signUpDTO) {
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

	@Override
	public CapacityDTO getCapacity(EmailDTO emailDTO) {
		return userDao.getCapacity(emailDTO);
	}
}
