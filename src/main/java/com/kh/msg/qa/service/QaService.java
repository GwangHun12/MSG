package com.kh.msg.qa.service;

import java.util.List;
import java.util.Map;

import com.kh.msg.qa.domain.PageInfo;
import com.kh.msg.qa.domain.Qa;

public interface QaService {

	Integer getListCount();

	int insertQa(Qa qa);

	List<Qa> selectQaList(PageInfo pInfo);

	Qa selectQaByNo(Integer qaNo);

	int deleteQa(Qa qa);

	int updateQa(Qa qa);

	List<Qa> searchQaByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	int updateViewCount(Integer qaNo);

	int updateQaByNo(int qaNo);

	int updateReplyNum(Map<String, Integer> replyCountInfo);







}
