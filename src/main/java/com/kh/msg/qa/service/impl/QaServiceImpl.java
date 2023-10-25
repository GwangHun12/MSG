package com.kh.msg.qa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.qa.domain.PageInfo;
import com.kh.msg.qa.domain.Qa;
import com.kh.msg.qa.service.QaService;
import com.kh.msg.qa.store.QaStore;

@Service
public class QaServiceImpl implements QaService{

	@Autowired
	private QaStore qStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Integer getListCount() {
		int result = qStore.selectListCount(sqlSession);
		return result;
	}
	@Override
	public int insertQa(Qa qa) {
		int result = qStore.insertQa(sqlSession, qa);
		return result;
	}
	@Override
	public List<Qa> selectQaList(PageInfo pInfo) {
		List<Qa> qList = qStore.selectQaList(sqlSession);
		return qList;
	}
	@Override
	public Qa selectQaByNo(Integer qaNo) {
		Qa qa = qStore.selectQaByNo(sqlSession, qaNo);
		return qa;
	}
	@Override
	public int deleteQa(Qa qa) {
		int result = qStore.deleteQa(sqlSession, qa);
		return result;
	}
	@Override
	public int updateQa(Qa qa) {
		int result = qStore.updateQa(sqlSession, qa);
		return result;
	}
	@Override
	public List<Qa> searchQaByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Qa> searchList = qStore.selectQaByKeyword(sqlSession, pInfo, paramMap);
		return searchList;
	}
	@Override
	public int updateViewCount(Integer qaNo) {
		int result = qStore.updateView(sqlSession, qaNo);
		return result;
	}
	@Override
	public int updateQaByNo(int qaNo) {
		int result = qStore.updateQaByNo(sqlSession, qaNo);
		return result;
	}
	@Override
	public int updateReplyNum(Map<String, Integer> replyCountInfo) {
		int result = qStore.updateReplyNum(sqlSession, replyCountInfo);
		return result;
	}
	
		
}
