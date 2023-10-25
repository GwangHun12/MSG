package com.kh.msg.notice.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.notice.domain.Notice;
import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Override
	public int insertNotice(SqlSession sqlSession, Notice notice) {
		int result = sqlSession.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public int selectListCount(SqlSession sqlSession) {
		int result = sqlSession.selectOne("NoticeMapper.selectListCount");
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(SqlSession sqlSession, PageInfo pInfo) {
		int limit = pInfo.getNaviCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = sqlSession.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public Notice selectNoticeByNo(SqlSession sqlSession, Integer noticeNo) {
		Notice notice = sqlSession.selectOne("NoticeMapper.selectNoticeByNo", noticeNo);
		return notice;
	}

	@Override
	public int deleteNotice(SqlSession sqlSession, Notice notice) {
		int result = sqlSession.delete("NoticeMapper.deleteNotice", notice);
		return result;
	}

	@Override
	public int updateNotice(SqlSession sqlSession, Notice notice) {
		int result = sqlSession.update("NoticeMapper.updateNotice", notice);
		return result;
	}

	@Override
	public List<Notice> selectNoticeByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> searchList = sqlSession.selectList("NoticeMapper.selectNoticeByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public List<Notice> allNoticeList(SqlSession sqlSession) {
		List<Notice> nList = sqlSession.selectList("NoticeMapper.allNoticeList");
		return nList;
	}

}
