package com.inzent.medialibrary.security;

import com.inzent.medialibrary.dto.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{
//	private static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);
//	private static final byte[] KEY = JwtProperties.SECRET.getBytes(StandardCharsets.UTF_8);
	private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
//	JwtProperties.SECRET;
	
	public String getToken(UserVO user) {
//		System.out.println(KEY.getAlgorithm());
//		System.out.println(KEY.getFormat());
//		System.out.println(KEY.getEncoded());
//		for(byte b : KEY.getEncoded()) {
//			System.out.print(b);
//		}
//		System.out.println("");
//		System.out.println(KEY.getClass());
        return Jwts.builder()
                .setSubject(user.getUser_email())
                .setExpiration(new Date(System.currentTimeMillis() + JwtProperties.TOKEN_EXPIRATION_TIME))
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .claim("useremail", user.getUser_email())
                .claim("username",user.getUser_name())
                .signWith(KEY)
                .compact();
    }
	
//    @Override
    public boolean isUsable(String jwt) {
//        try{
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
//            System.out.println(jwt);
//            System.out.println(Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getSignature());
//            System.out.println(Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody());
//            System.out.println(Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getHeader());
            return true;
//        } catch (JwtException e) {
//            log.info("JwtException  : " + e.getMessage());
//            throw new UnauthorizedException();
//        }
    }
    
    public String getUserInfo(String key){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println(jwtToken);
        Jws<Claims> jws;

//        try{
        jws = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(jwtToken);
//        } catch (Exception e) {
//            throw new UnauthorizedException();
//        }
        System.out.println(jws);
        System.out.println(jws.getSignature());
        System.out.println(jws.getBody());
        System.out.println(jws.getBody().getSubject());
        return jws.getBody().get(key, String.class);
    }
}
