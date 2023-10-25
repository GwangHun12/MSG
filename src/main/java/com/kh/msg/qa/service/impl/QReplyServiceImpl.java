package com.kh.msg.qa.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.qa.domain.QReply;
import com.kh.msg.qa.service.QReplyService;
import com.kh.msg.qa.store.QReplyStore;

@Service
public class QReplyServiceImpl implements QReplyService{

	@Autowired
	private QReplyStore qrStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<QReply> selectQReplyList(Integer qaNo) {
		List<QReply> qrList = qrStore.selectQReplyList(sqlSession, qaNo);
		return qrList;
	}
	@Override
	public int insertReply(QReply qreply) {
		int result = qrStore.insertReply(sqlSession, qreply);
		return result;
	}
	@Override
	public int updateReply(QReply qreply) {
		int result = qrStore.updateReply(sqlSession, qreply);
		return result;
	}
	@Override
	public int deleteReply(QReply qreply) {
		int result = qrStore.deleteReply(sqlSession, qreply);
		return result;
	}
	@Override
	public int getReplyCountByQaNo(int qaNo) {
		int result = qrStore.getReplyCountByQaNo(sqlSession, qaNo);
		return result;
	}
	
	
}
