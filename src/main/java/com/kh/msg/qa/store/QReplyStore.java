package com.kh.msg.qa.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.qa.domain.QReply;


public interface QReplyStore {

	List<QReply> selectQReplyList(SqlSession sqlSession, Integer qaNo);

	int insertReply(SqlSession sqlSession, QReply qreply);

	int updateReply(SqlSession sqlSession, QReply qreply);

	int deleteReply(SqlSession sqlSession, QReply qreply);

	int getReplyCountByQaNo(SqlSession sqlSession, int qaNo);

}
