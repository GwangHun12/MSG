package com.kh.msg.project.domain;

public class SideProject {
	private int sproNo;
	private String sproName;
	private int sproProjectNo;
	
	public int getSproNo() {
		return sproNo;
	}
	public void setSproNo(int sproNo) {
		this.sproNo = sproNo;
	}
	public String getSproName() {
		return sproName;
	}
	public void setSproName(String sproName) {
		this.sproName = sproName;
	}
	public int getSproProjectNo() {
		return sproProjectNo;
	}
	public void setSproProjectNo(int sproProjectNo) {
		this.sproProjectNo = sproProjectNo;
	}
	@Override
	public String toString() {
		return "SideProject [sproNo=" + sproNo + ", sproName=" + sproName + ", sproProjectNo=" + sproProjectNo + "]";
	}
	
	
}
