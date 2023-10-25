package com.kh.msg.admin.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.user.domain.User;

public interface AdminStore {

	int selectListCount(SqlSession sqlSession);

	List<User> selectUserList(SqlSession sqlSession, PageInfo pInfo);

	int deleteUser(SqlSession sqlSession, String userId);

	int deleteNotice(SqlSession sqlSession, Integer noticeNo);

	int getListCount(SqlSession sqlSession, Map<String, String> paramMap);

	List<User> searchUserByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap);

}
