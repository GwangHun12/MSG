package com.kh.msg.user.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.user.domain.User;

public interface accountStore {


	User memberIdSearch(SqlSession session, User searchVO);

	int memberPwdCheck(SqlSession session, User searchVO);

	void passwordUpdate(SqlSession session, User searchVO);

}
