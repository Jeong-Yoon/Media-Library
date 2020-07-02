package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
	private String user_id;
	private String user_type;
	private String user_email;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_company;
	private String user_department;
	private String user_job;
	private String user_reg_date;
	private String user_update_date;
}
