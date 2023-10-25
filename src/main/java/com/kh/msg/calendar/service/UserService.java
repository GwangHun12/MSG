package com.kh.msg.calendar.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.msg.calendar.domain.UserVO;
public interface UserService {
	public String getTime();
	
	public void signup(UserVO vo);
	
	public UserVO read(String id);
	
	public List<UserVO> list(String query);
	
	public int idchk(String id);

}
