package com.inzent.medialibrary.security;

import com.inzent.medialibrary.dto.LoginUserDTO;

public interface JwtService {
	public String getToken(LoginUserDTO user);
	public boolean isUsable(String jwt);
	public String getUserInfo(String key);
}
