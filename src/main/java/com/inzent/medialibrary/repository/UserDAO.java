package com.inzent.medialibrary.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.LoginUserDTO;
import com.inzent.medialibrary.dto.SignUpDTO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;

	public LoginUserDTO login(LoginDTO loginDto) {
		LoginUserDTO user = sqlSession.selectOne("getuserbyemail", loginDto.getEmail());
		return user;
	}

	public int signup(SignUpDTO signUpDTO) {
		return sqlSession.insert("signup",signUpDTO);
	}

	public int emailChk(String email) {
		return sqlSession.selectOne("emailchk", email);
	}

	public long getUserIdByEmail(String email) {
		return sqlSession.selectOne("getuseridbyemail", email);
	}

	public String getFolderPathById(Long parent) {
		return sqlSession.selectOne("getfolderpathbyid", parent);
	}
	
	
}
