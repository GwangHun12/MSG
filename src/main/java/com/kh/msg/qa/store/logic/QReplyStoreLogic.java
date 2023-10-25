package com.kh.msg.qa.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.qa.domain.QReply;
import com.kh.msg.qa.store.QReplyStore;

@Repository
public class QReplyStoreLogic implements QReplyStore{

	@Override
	public List<QReply> selectQReplyList(SqlSession sqlSession, Integer qaNo) {
		List<QReply> qrList = sqlSession.selectList("QReplyMapper.selectQReplyList", qaNo);
		return qrList;
	}

	@Override
	public int insertReply(SqlSession sqlSession, QReply qreply) {
		int result = sqlSession.insert("QReplyMapper.insertQReply", qreply);
		return result;
	}

	@Override
	public int updateReply(SqlSession sqlSession, QReply qreply) {
		int result = sqlSession.update("QReplyMapper.updateQReply", qreply);
		return result;
	}

	@Override
	public int deleteReply(SqlSession sqlSession, QReply qreply) {
		int result = sqlSession.delete("QReplyMapper.deleteQReply", qreply);
		return result;
	}

	@Override
	public int getReplyCountByQaNo(SqlSession sqlSession, int qaNo) {
		int result = sqlSession.selectOne("QReplyMapper.getReplyCountByQaNo", qaNo);
		return result;
	}

}
