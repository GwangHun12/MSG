package com.kh.msg.admin.service;

import java.util.List;
import java.util.Map;

import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.user.domain.User;

public interface AdminService {

	Integer getListCount();

	List<User> selectUserList(PageInfo pInfo);

	int deleteUser(String userId);

	int deleteNotice(Integer noticeNo);

	int getListCount(Map<String, String> paramMap);

	List<User> searchUserByKeyword(PageInfo pInfo, Map<String, String> paramMap);

}
