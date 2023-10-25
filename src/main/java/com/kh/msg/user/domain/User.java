package com.kh.msg.user.domain;

import java.sql.Timestamp;

public class User {
	private String userId;
	private String userPw;
	private String userNickName;
	private String userName;
	private String userPhone;
	private String userAdd;
	private String userEmail;
	private Timestamp userDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserAdd() {
		return userAdd;
	}
	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Timestamp getUserDate() {
		return userDate;
	}
	public void setUserDate(Timestamp userDate) {
		this.userDate = userDate;
	}
	
	public User(String userId, String userPw, String userNickName, String userName, String userPhone, String userAdd,
			String userEmail, Timestamp userDate) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNickName = userNickName;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAdd = userAdd;
		this.userEmail = userEmail;
		this.userDate = userDate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", userAdd=" + userAdd + ", userName=" + userName
				+ ", userNickName=" + userNickName + ", userPhone=" + userPhone + ", userEmail=" + userEmail
				+ ", userDate=" + userDate + "]";
	}
	
	
}
