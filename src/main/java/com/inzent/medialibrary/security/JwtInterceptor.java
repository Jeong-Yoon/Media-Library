package com.inzent.medialibrary.security;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor{
	
    private final JwtService jwtService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(CorsUtils.isPreFlightRequest(request)){
            response.setStatus(response.SC_OK);
            return true;
        }

        final String token = request.getHeader(JwtProperties.HEADER_STRING);

        if(token != null && jwtService.isUsable(token)){
            return true;
        }

        throw new RuntimeException();
    }
}
