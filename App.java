package com.sbs.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.controller.MemberControler;
import com.sbs.java.ssg.dto.Article;
import com.sbs.java.ssg.dto.Members;
import com.sbs.java.ssg.util.Util;

public class App {
	ArrayList<Article> arr = new ArrayList<>();
	List<Members> members = new ArrayList<>();
	MemberControler membercontroler = new MemberControler();
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		MakeTestCase();
		
		System.out.println("==프로그램 시작==");
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			int lastArticleId =0;
			// 명령어 없을 때
			if(cmd.length() ==0) {
				continue;
			}
			
			// 프로그램 종료
			if(cmd.equals("system exit")) {
				System.out.printf("==프로그램 끝==");
				break;
			}
			// 회원가입
			if(cmd.equals("member join")) {
				membercontroler.doJoin();;
			}
			// 글 추가
			if(cmd.equals("article write")) {
				
				int id = arr.size()+1;
				String regDate = Util.getNowDatestr();
				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String body = sc.nextLine();
				
				Article at =  new Article(id,regDate,title,body);		
				arr.add(at);
				System.out.printf("%d번 글이 생성되었습니다.\n",at.id);
			} 
			
			// 글 보기
			else if(cmd.startsWith("article list")) {
				if(arr.size() ==0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				String SearchKeyword = cmd.substring("article list".length()).trim();
				
				List<Article> forListArticles = arr;
				
				if(SearchKeyword.length() > 0) {
					forListArticles = new ArrayList<>();
					
					for(Article article : arr) {
						if(article.title.contains(SearchKeyword)) {
							forListArticles.add(article);
						}
					}
					if(arr.size()==0) {
						System.out.println("검색결과가 존재하지 않습니다.");
						continue;
					}
				}

				System.out.println("번호 | 조회 | 제목");
				for(int i=forListArticles.size()-1;i>=0;i--) {
					Article at =forListArticles.get(i);
					
					System.out.printf("%d    | %d    | %s \n",at.id,at.hit,at.title);					
				}
			}
			
			// 글 상세 보기
			else if(cmd.startsWith("article detail")) {
				
				int id = CmdBits(cmd);
				
				try {
					Article foundArticle = getArticleById(id);

					foundArticle.increaseHit();
					System.out.println("번호: "+foundArticle.id);
					System.out.println("날짜: "+foundArticle.regDate);
					System.out.println("제목: "+foundArticle.title);
					System.out.println("내용: "+foundArticle.body);
					System.out.println("조회: "+foundArticle.hit);
				}
				catch(Exception e) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
					continue;
				}

			}
			
			// 게시물 삭제기능
			else if(cmd.startsWith("article delete")) {
				String [] cmdbits = cmd.split(" ");
				int id = Integer.parseInt(cmdbits[2]);
				
				int fonundIndex = getArticleIndexById(id);;
				
				if(fonundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
					continue;
				}
				arr.remove(fonundIndex);
				System.out.printf("%d번 게시물을 삭제되었습니다\n.",id);
			}
			
			// 게시물 수정
			else if(cmd.startsWith("article modify")) {
				int id= CmdBits(cmd);
	
				Article foundArticle = getArticleById(id);
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
					continue;
				}
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.println("수정이 완료되었습니다.");
			}
			// 잘못된 명령어
			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어입니다.\n",cmd);
			}
		}
	}
	
	
	
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : arr) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return arr.get(index);
		}
		return null;
	}
	
	private int CmdBits(String cmd) {
		String [] cmdbits = cmd.split(" ");
		int id = Integer.parseInt(cmdbits[2]);
		return id;
	}

	private void MakeTestCase() {
		arr.add(new Article(1,Util.getNowDatestr(),"안녕","aaaa",11));
		arr.add(new Article(2,Util.getNowDatestr(),"안녕2","bbbb",22));
		arr.add(new Article(3,Util.getNowDatestr(),"안녕3","cccc",33));
	}
}
