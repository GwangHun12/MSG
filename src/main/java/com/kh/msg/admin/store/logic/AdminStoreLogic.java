package com.kh.msg.admin.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.admin.store.AdminStore;
import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.user.domain.User;

@Repository
public class AdminStoreLogic implements AdminStore{

	@Override
	public int selectListCount(SqlSession sqlSession) {
		int result = sqlSession.selectOne("UserMapper.selectListCount");
		return result;
	}

	@Override
	public List<User> selectUserList(SqlSession sqlSession, PageInfo pInfo) {
		int limit = pInfo.getNaviCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<User> uList = sqlSession.selectList("UserMapper.selectUserList", null, rowBounds);
		return uList;
	}

	@Override
	public int deleteUser(SqlSession sqlSession, String userId) {
		int result = sqlSession.delete("UserMapper.deleteUser", userId);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession sqlSession, Integer noticeNo) {
		int result = sqlSession.delete("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}

	@Override
	public int getListCount(SqlSession sqlSession, Map<String, String> paramMap) {
		int result = sqlSession.selectOne("UserMapper.getListCount", paramMap);
		return result;
	}

	@Override
	public List<User> searchUserByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getNaviCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<User> searchUserList = sqlSession.selectList("UserMapper.searchUserByKeyword", paramMap, rowBounds);
		return searchUserList;
	}

}
