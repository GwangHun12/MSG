package com.kh.msg.user.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.user.domain.User;
import com.kh.msg.user.store.accountStore;

@Repository
public class accoutStoreLogic implements accountStore {

	@Override
	public User memberIdSearch(SqlSession session, User searchVO) {
		// TODO Auto-generated method stub
		User user = session.selectOne("UserMapper.memberIdSearch", searchVO);
		
		return user;
	}

	@Override
	public int memberPwdCheck(SqlSession session, User searchVO) {
		// TODO Auto-generated method stub
		int result = session.selectOne("UserMapper.memberPwdCheck", searchVO);
		return result;
	}

	@Override
	public void passwordUpdate(SqlSession session, User searchVO) {
		// TODO Auto-generated method stub
		 session.selectOne("UserMapper.passwordUpdate", searchVO);
	
	}
	

	
	
}
