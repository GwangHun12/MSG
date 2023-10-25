package com.kh.msg.calendar.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kh.msg.calendar.domain.CalVO;
import com.kh.msg.calendar.domain.GuVO;

public interface calService {
	int makeCal(CalVO vo);

	// 마스터 유저 삭제
	int deleteCal(String id);

	// 내가 가입한 그룹 코드 검색
	 GuVO calmygroup(@Param("id") String id, @Param("g_code") int g_code);

	// g_code 일정 리스트 가져오기
	 List<CalVO> scheduleList(Integer g_code);
	
	// 아이디로 일정 리스트 가져오기
	 List<CalVO> myscheduleList(String id);

	// insert
	int scheduleinsert(CalVO vo);

	// 전체 update
	public int scheduleupdate(CalVO vo);

	// dragupdate
	public int dragupdate(CalVO vo);

	// 삭제
	public int scheduledelete(int calno);

	List<CalVO> mymineproject(String userId);

	List<CalVO> projectproject(int projectNo);

}
