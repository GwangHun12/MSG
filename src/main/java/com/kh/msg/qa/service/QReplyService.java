package com.kh.msg.qa.service;

import java.util.List;

import com.kh.msg.qa.domain.QReply;

public interface QReplyService {

	List<QReply> selectQReplyList(Integer qaNo);

	int insertReply(QReply qreply);

	int updateReply(QReply qreply);

	int deleteReply(QReply qreply);

	int getReplyCountByQaNo(int qaNo);


}
