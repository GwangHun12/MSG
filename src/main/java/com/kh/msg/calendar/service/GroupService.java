package com.kh.msg.calendar.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.msg.calendar.domain.GroupVO;
import com.kh.msg.calendar.domain.GuVO;
import com.kh.msg.calendar.domain.UserVO;

public interface GroupService {
	
	public void makeGroup(GroupVO vo);
	
	public List<GuVO> mygroup(String id);
	
	public List<UserVO> guget(GroupVO vo);
	
	public List<GuVO> GroupList(String id);
	
	public GroupVO gread(int g_code);
	
	public UserVO upic(String id);
	
	//마스터 유저 삭제
	public void deletegroup(int g_code);
	
}
