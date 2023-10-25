package com.kh.msg.user.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.user.domain.User;

 
public interface UserStore {

	int insertUser(SqlSession session, User user);

	User checkUserLogin(SqlSession session, User user);

	int updateUser(SqlSession session, User user);

//	int deleteUser(SqlSession session, String userId);

	void memberDelete(User vo) throws Exception;
	
	
	int idChk(UserStore vo) throws Exception;

	User getUserById(SqlSession session, String userId);
	
	public int idCheck(String userId);


	
}
