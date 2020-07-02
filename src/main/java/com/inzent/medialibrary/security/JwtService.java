package com.inzent.medialibrary.security;

import com.inzent.medialibrary.dto.UserVO;

public interface JwtService {
	public String getToken(UserVO user);
	public boolean isUsable(String jwt);
	public String getUserInfo(String key);
}
