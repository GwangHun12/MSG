package com.kh.msg.qa.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.qa.domain.PageInfo;
import com.kh.msg.qa.domain.Qa;

public interface QaStore {

	int selectListCount(SqlSession sqlSession);

	int insertQa(SqlSession sqlSession, Qa qa);

	List<Qa> selectQaList(SqlSession sqlSession);

	Qa selectQaByNo(SqlSession sqlSession, Integer qaNo);

	int deleteQa(SqlSession sqlSession, Qa qa);

	int updateQa(SqlSession sqlSession, Qa qa);

	List<Qa> selectQaByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap);

	int updateView(SqlSession sqlSession, Integer qaNo);

	int updateQaByNo(SqlSession sqlSession, int qaNo);

	int updateReplyNum(SqlSession sqlSession, Map<String, Integer> replyCountInfo);






}
