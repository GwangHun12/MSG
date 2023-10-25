package com.kh.msg.qa.domain;

import java.sql.Timestamp;

public class Qa {

	private int qaNo;
	private String qaTitle;
	private String qaContent;
	private Timestamp qCreateDate;
	private Timestamp qUpdateDate;
	private int qaSecret;
	private String userId;
	private String password;
	private int viewCount;
	private int commentCount;
	
	public int getQaNo() {
		return qaNo;
	}
	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}
	public String getQaTitle() {
		return qaTitle;
	}
	public void setQaTitle(String qaTitle) {
		this.qaTitle = qaTitle;
	}
	public String getQaContent() {
		return qaContent;
	}
	public void setQaContent(String qaContent) {
		this.qaContent = qaContent;
	}
	public Timestamp getqCreateDate() {
		return qCreateDate;
	}
	public void setqCreateDate(Timestamp qCreateDate) {
		this.qCreateDate = qCreateDate;
	}
	public Timestamp getqUpdateDate() {
		return qUpdateDate;
	}
	public void setqUpdateDate(Timestamp qUpdateDate) {
		this.qUpdateDate = qUpdateDate;
	}
	public int getQaSecret() {
		return qaSecret;
	}
	public void setQaSecret(int qaSecret) {
		this.qaSecret = qaSecret;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "질문게시판 [질문번호=" + qaNo + ", 질문제목=" + qaTitle + ", 질문내용=" + qaContent + ", 작성일=" + qCreateDate
				+ ", 수정일=" + qUpdateDate + ", 비밀번호=" + qaSecret + ", 유저아이디=" + userId + ", 비밀번호=" + password 
						+ ", 조회수=" + viewCount + ", 댓글수=" + commentCount + "]";
	}
	
	
}
