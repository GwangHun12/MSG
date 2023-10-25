package com.kh.msg.calendar.store;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.calendar.domain.CalVO;
import com.kh.msg.calendar.domain.GuVO;

public interface CalStore {

	List<CalVO> listCalendar(Integer g_code, SqlSession session);

	int insertcalendar(CalVO vo, SqlSession session);

	List<CalVO> myscheduleList(String id, SqlSession session);

	int makecalendar(CalVO vo, SqlSession session);

	int deletecalendar(String id, SqlSession session);

	GuVO selectgroup(String id, int g_code, SqlSession session);

	int deleteschedule(int calno, SqlSession session);

	int updateschedule(CalVO vo, SqlSession session);

	int updatedrag(CalVO vo, SqlSession session);

	List<CalVO> listlistcalendar(String userId,SqlSession session);

	List<CalVO> listprojectcalendar(int projectNo, SqlSession session);

}
