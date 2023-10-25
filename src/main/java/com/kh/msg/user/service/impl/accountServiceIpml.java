package com.kh.msg.user.service.impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.user.domain.User;
import com.kh.msg.user.service.accountService;
import com.kh.msg.user.store.accountStore;


@Service
public class accountServiceIpml implements accountService {

	@Autowired
	private SqlSession session;
	
	@Autowired
	private accountStore aStore;
	
	@Override
	public User memberIdSearch(User searchVO) {
		// TODO Auto-generated method stub
		User user = aStore.memberIdSearch(session, searchVO);
		return user;
	}

	@Override
	public int memberPwdCheck(User searchVO) {
		// TODO Auto-generated method stub
		return aStore.memberPwdCheck(session, searchVO);
		
	}
		

	@Override
	public void passwordUpdate(User searchVO) {
		// TODO Auto-generated method stub
		 aStore.passwordUpdate(session, searchVO);
		
	}

	
	
	

}
