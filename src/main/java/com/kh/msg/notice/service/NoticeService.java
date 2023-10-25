package com.kh.msg.notice.service;

import java.util.List;
import java.util.Map;

import com.kh.msg.notice.domain.Notice;
import com.kh.msg.notice.domain.PageInfo;

public interface NoticeService {

	int insertNotice(Notice notice);

	Integer getListCount();

	List<Notice> selectNoticeList(PageInfo pInfo);

	Notice selectNoticeByNo(Integer noticeNo);

	int deleteNotice(Notice notice);

	int updateNotice(Notice notice);

	List<Notice> searchNoticeByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	List<Notice> allNoticeList();

}
