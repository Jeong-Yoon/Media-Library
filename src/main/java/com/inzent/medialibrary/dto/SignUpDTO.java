package com.inzent.medialibrary.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
	@Email
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String password2;
	@NotNull
	private String name;
	@NotNull
	private String phone;
	@NotNull
	private String company;
	@NotNull
	private String department;
	@NotNull
	private String job;
}
