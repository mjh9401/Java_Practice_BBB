package com.sbs.java.ssg.dto;

public class Members extends Dto{
	public String loginId;
	public String loginPw;
	public String name;
	
	public Members(int id, String regDate, String loginId2, String loginPw2, String name2) {
		this.id = id;
		this.regDate = regDate;
		loginId = loginId2;
		loginPw = loginPw2;
		name = name2;
	}

}
