package com.inzent.medialibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inzent.medialibrary.security.JwtService;
import com.inzent.medialibrary.security.JwtServiceImpl;

@SpringBootApplication
public class MedialibraryApplication {

	@Bean
	public JwtService jwtService() {
		return new JwtServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(MedialibraryApplication.class, args);
	}

}
