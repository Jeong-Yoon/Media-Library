package com.inzent.medialibrary.security;

public class JwtProperties {
    public static final String SECRET = "mlsecret";
    public static final Long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24L; // 1 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
}
