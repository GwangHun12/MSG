package com.kh.msg.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.notice.domain.Notice;
import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.notice.service.NoticeService;
import com.kh.msg.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(sqlSession, notice);
		return result;
	}

	@Override
	public Integer getListCount() {
		int result = nStore.selectListCount(sqlSession);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(sqlSession, pInfo);
		return nList;
	}

	@Override
	public Notice selectNoticeByNo(Integer noticeNo) {
		Notice notice = nStore.selectNoticeByNo(sqlSession, noticeNo);
		return notice;
	}

	@Override
	public int deleteNotice(Notice notice) {
		int result = nStore.deleteNotice(sqlSession, notice);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		int result = nStore.updateNotice(sqlSession, notice);
		return result;
	}

	@Override
	public List<Notice> searchNoticeByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Notice> searchList = nStore.selectNoticeByKeyword(sqlSession, pInfo, paramMap);
		return searchList;
	}

	@Override
	public List<Notice> allNoticeList() {
		List<Notice> nList = nStore.allNoticeList(sqlSession);
		return nList;
	}

}
