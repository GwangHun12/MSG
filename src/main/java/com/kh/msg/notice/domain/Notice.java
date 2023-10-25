package com.kh.msg.notice.domain;

import java.sql.Timestamp;

public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Timestamp nCreateDate;
	private Timestamp nUpdateDate;
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Timestamp getnCreateDate() {
		return nCreateDate;
	}
	public void setnCreateDate(Timestamp nCreateDate) {
		this.nCreateDate = nCreateDate;
	}
	public Timestamp getnUpdateDate() {
		return nUpdateDate;
	}
	public void setnUpdateDate(Timestamp nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}
	
	@Override
	public String toString() {
		return "공지사항 [공지번호=" + noticeNo
				+ "공지제목=" + noticeTitle + ", 공지내용=" + noticeContent
				+ ", 작성일=" + nCreateDate
				+ ", 수정일=" + nUpdateDate + "]";
	}
	
	
}
