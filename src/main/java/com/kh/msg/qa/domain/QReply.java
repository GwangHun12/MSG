package com.kh.msg.qa.domain;

import java.sql.Timestamp;

public class QReply {

	private int replyNo;
	private String replyContent;
	private Timestamp rCreateDate;
	private Timestamp rUpdateDate;
	private int qaNo;
	private int secret;
	private String replyStatus;
	
	private String userId;
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Timestamp getrCreateDate() {
		return rCreateDate;
	}
	public void setrCreateDate(Timestamp rCreateDate) {
		this.rCreateDate = rCreateDate;
	}
	public Timestamp getrUpdateDate() {
		return rUpdateDate;
	}
	public void setrUpdateDate(Timestamp rUpdateDate) {
		this.rUpdateDate = rUpdateDate;
	}
	public int getQaNo() {
		return qaNo;
	}
	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
	
	public String getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}
	@Override
	public String toString() {
		return "질문댓글 [댓글번호=" + replyNo + ", 댓글내용=" + replyContent + ", 작성일=" + rCreateDate
				+ ", 수정일=" + rUpdateDate + ", 질문번호=" + qaNo + ", 유저아이디=" + userId + ", 비밀여부=" + secret
				+ ", 댓글여부=" + replyStatus + "]";
	}
	
	
}
