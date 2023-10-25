package com.kh.msg.project.domain;

public class Project {
	private int projectNo;
	private String projectName;
	private String projectCreator;
	private String projectMember;
	private String userName;
	
	public Project() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Project(int projectNo, String projectName, String projectCreator, String projectMember) {
		super();
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.projectCreator = projectCreator;
		this.projectMember = projectMember;
	}

	public Project(int projectNo, String projectName) {
		super();
		this.projectNo = projectNo;
		this.projectName = projectName;
	}

	public Project(int projectNo, String projectName, String projectCreator) {
		super();
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.projectCreator = projectCreator;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCreator() {
		return projectCreator;
	}

	public void setProjectCreator(String projectCreator) {
		this.projectCreator = projectCreator;
	}

	public String getProjectMember() {
		return projectMember;
	}

	public void setProjectMember(String projectMember) {
		this.projectMember = projectMember;
	}

	@Override
	public String toString() {
		return "Project [projectNo=" + projectNo + ", projectName=" + projectName + ", projectCreator=" + projectCreator
				+ ", projectMember=" + projectMember + "]";
	}

}
