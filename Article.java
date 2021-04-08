package com.sbs.java.ssg.dto;

public class Article extends Dto{ 
	public String title;
	public String body;
	public int hit;
	
	public Article(int id2, String regDate, String title2, String body2) {
		id = id2;
		this.regDate = regDate;
		title = title2;
		body = body2;
	}
	
	public Article(int id2, String regDate, String title2, String body2, int hit) {
		id = id2;
		this.regDate = regDate;
		title = title2;
		body = body2;
		this.hit = hit;
	}
	public void increaseHit() {
		hit++;;
	}
}