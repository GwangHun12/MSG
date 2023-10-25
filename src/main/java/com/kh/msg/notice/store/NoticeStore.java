package com.kh.msg.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.notice.domain.Notice;
import com.kh.msg.notice.domain.PageInfo;

public interface NoticeStore {

	int insertNotice(SqlSession sqlSession, Notice notice);

	int selectListCount(SqlSession sqlSession);

	List<Notice> selectNoticeList(SqlSession sqlSession, PageInfo pInfo);

	Notice selectNoticeByNo(SqlSession sqlSession, Integer noticeNo);

	int deleteNotice(SqlSession sqlSession, Notice notice);

	int updateNotice(SqlSession sqlSession, Notice notice);

	List<Notice> selectNoticeByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap);

	List<Notice> allNoticeList(SqlSession sqlSession);

}
