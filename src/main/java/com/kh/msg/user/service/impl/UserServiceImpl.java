package com.kh.msg.user.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.user.domain.User;
import com.kh.msg.user.service.UserService;
import com.kh.msg.user.store.UserStore;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private SqlSession session;

	@Autowired
	private UserStore mStore;
	
	@Override
	public int insertuser(User user) {
		int result = mStore.insertUser(session, user);
		return result;
	}
	@Override
	public int updateUser(User user) {
		int result = mStore.updateUser(session, user);
		return result;
	 
	}
//	@Override
//	public int deleteUser(String userId) {
//		int result = mStore.deleteUser(session, userId);
//		return result;
//	}

	@Override
	public User checkUserLogin(User user) {
		User mOne = mStore.checkUserLogin(session, user);
		return mOne;
	}

	@Override
	public User getUserById(String userId) {
		User user = mStore.getUserById(session, userId);
		return user;
	}

	@Override
	public int idChk(UserStore vo) throws Exception{
		// TODO Auto-generated method stub
		int result = mStore.idChk(vo);
		return result;
	}
	
	@Override
	public int idCheck(String userId) throws Exception {
		
		return mStore.idCheck(userId);
	}
		
	@Override
	public void register(UserStore vo) {
			// TODO Auto-generated method stub
			
	}
	@Override
	public void memberDelete(User vo) throws Exception {
		// TODO Auto-generated method stub
		mStore.memberDelete(vo);
	}
	
	
	

	

	
}
