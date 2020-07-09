package com.inzent.medialibrary.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.LoginDTO;
import com.inzent.medialibrary.dto.SignUpDTO;
import com.inzent.medialibrary.dto.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;

	public UserVO login(LoginDTO loginDto) {
		UserVO userVo = sqlSession.selectOne("getuserbyemail", loginDto.getEmail());
		return userVo;
	}

	public long signup(SignUpDTO signUpDTO) {
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
