package com.kh.msg.project.domain;

import java.sql.Date;

public class SideProjectBoard {
	private int spbNo;
	private String spbContent;
	private String spbWriter;
	private Date spbCreateDate;
	private Date spbUpdateDate;
	private String updateYN;
	private int spbProjectNo;
	
	public SideProjectBoard() {}

	public SideProjectBoard(String spbContent, String spbWriter, int spbProjectNo) {
		super();
		this.spbContent = spbContent;
		this.spbWriter = spbWriter;
		this.spbProjectNo = spbProjectNo;
	}

	public int getSpbNo() {
		return spbNo;
	}

	public void setSpbNo(int spbNo) {
		this.spbNo = spbNo;
	}

	public String getSpbContent() {
		return spbContent;
	}

	public void setSpbContent(String spbContent) {
		this.spbContent = spbContent;
	}

	public String getSpbWriter() {
		return spbWriter;
	}

	public void setSpbWriter(String spbWriter) {
		this.spbWriter = spbWriter;
	}

	public Date getSpbCreateDate() {
		return spbCreateDate;
	}

	public void setSpbCreateDate(Date spbCreateDate) {
		this.spbCreateDate = spbCreateDate;
	}

	public Date getSpbUpdateDate() {
		return spbUpdateDate;
	}

	public void setSpbUpdateDate(Date spbUpdateDate) {
		this.spbUpdateDate = spbUpdateDate;
	}

	public String getUpdateYN() {
		return updateYN;
	}

	public void setUpdateYN(String updateYN) {
		this.updateYN = updateYN;
	}

	public int getSpbProjectNo() {
		return spbProjectNo;
	}

	public void setSpbProjectNo(int spbProjectNo) {
		this.spbProjectNo = spbProjectNo;
	}

	@Override
	public String toString() {
		return "SideProjectBoard [spbNo=" + spbNo + ", spbContent=" + spbContent + ", spbWriter=" + spbWriter
				+ ", spbCreateDate=" + spbCreateDate + ", spbUpdateDate=" + spbUpdateDate + ", updateYN=" + updateYN
				+ ", spbProjectNo=" + spbProjectNo + "]";
	}

}