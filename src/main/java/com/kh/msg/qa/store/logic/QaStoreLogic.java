package com.kh.msg.qa.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.qa.domain.PageInfo;
import com.kh.msg.qa.domain.Qa;
import com.kh.msg.qa.store.QaStore;

@Repository
public class QaStoreLogic implements QaStore{

	@Override
	public int selectListCount(SqlSession sqlSession) {
		int result = sqlSession.selectOne("QaMapper.selectListCount");
		return result;
	}

	@Override
	public int insertQa(SqlSession sqlSession, Qa qa) {
		int result = sqlSession.insert("QaMapper.insertQa", qa);
		return result;
	}

	@Override
	public List<Qa> selectQaList(SqlSession sqlSession) {
		List<Qa> qList = sqlSession.selectList("QaMapper.selectQaList");
		return qList;
	}

	@Override
	public Qa selectQaByNo(SqlSession sqlSession, Integer qaNo) {
		Qa qa = sqlSession.selectOne("QaMapper.selectQaByNo", qaNo);
		return qa;
	}

	@Override
	public int deleteQa(SqlSession sqlSession, Qa qa) {
		int result = sqlSession.delete("QaMapper.deleteQa", qa);
		return result;
	}

	@Override
	public int updateQa(SqlSession sqlSession, Qa qa) {
		int result = sqlSession.update("QaMapper.updateQa", qa);
		return result;
	}

	@Override
	public List<Qa> selectQaByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Qa> searchList = sqlSession.selectList("QaMapper.selectQaByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public int updateView(SqlSession sqlSession, Integer qaNo) {
		int result = sqlSession.update("QaMapper.updateViewCount", qaNo);
		return result;
	}

	@Override
	public int updateQaByNo(SqlSession sqlSession, int qaNo) {
		int result = sqlSession.update("QaMapper.updateQaByNo", qaNo);
		return result;
	}

	@Override
	public int updateReplyNum(SqlSession sqlSession, Map<String, Integer> replyCountInfo) {
		int result = sqlSession.update("QaMapper.updateReplyNum", replyCountInfo);
		return result;
	}






}
