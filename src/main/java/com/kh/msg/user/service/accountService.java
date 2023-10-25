package com.kh.msg.user.service;

import com.kh.msg.user.domain.User;

public interface accountService {

	 User memberIdSearch(User searchVO);
		// TODO Auto-generated method stub

	int memberPwdCheck(User searchVO);

	void passwordUpdate(User searchVO);
		// TODO Auto-generated method stub
		
	
		
	
	
	

}
