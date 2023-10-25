package com.kh.msg.calendar.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.msg.project.domain.Project;

public class CalVO {
	private int calno;
	private String userName;
	private Project project;
	private int projectNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date end;
	private String title;
	private String description;
	private String type;
	private String backgroundColor;
	private String textColor;	
	private String allDay;
	private boolean allDayjs;
	private String schk;
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getCalno() {
		return calno;
	}

	public void setCalno(int calno) {
		this.calno = calno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTitle() {
	    return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}

	public boolean isAllDayjs() {
		return allDayjs;
	}

	public void setAllDayjs(boolean allDayjs) {
		this.allDayjs = allDayjs;
	}

	public String getSchk() {
		return schk;
	}

	public void setSchk(String schk) {
		this.schk = schk;
	}

	@Override
	public String toString() {
		return "CalVO [calno=" + calno + ", userName=" + userName + ", start=" + start + ", end=" + end + ", title="
				+ title + ", description=" + description + ", type=" + type + ", backgroundColor=" + backgroundColor
				+ ", textColor=" + textColor + ", allDay=" + allDay + ", allDayjs=" + allDayjs + ", schk=" + schk + "]";
	}

}
