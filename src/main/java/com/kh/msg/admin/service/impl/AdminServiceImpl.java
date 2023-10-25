package com.kh.msg.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.admin.service.AdminService;
import com.kh.msg.admin.store.AdminStore;
import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.user.domain.User;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminStore aStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Integer getListCount() {
		int result = aStore.selectListCount(sqlSession);
		return result;
	}

	@Override
	public List<User> selectUserList(PageInfo pInfo) {
		List<User> uList = aStore.selectUserList(sqlSession, pInfo);
		return uList;
	}

	@Override
	public int deleteUser(String userId) {
		int result = aStore.deleteUser(sqlSession, userId);
		return result;
	}

	@Override
	public int deleteNotice(Integer noticeNo) {
		int result = aStore.deleteNotice(sqlSession, noticeNo);
		return result;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = aStore.getListCount(sqlSession, paramMap);
		return result;
	}

	@Override
	public List<User> searchUserByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<User> searchUserList = aStore.searchUserByKeyword(sqlSession, pInfo, paramMap);
		return searchUserList;
	}

}
