package com.kh.msg.calendar.store.logic;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.calendar.domain.CalVO;
import com.kh.msg.calendar.domain.GuVO;
import com.kh.msg.calendar.store.CalStore;
@Repository
public class CalLogic implements CalStore{

	@Override
	public List<CalVO> listCalendar(Integer g_code, SqlSession session) {
		List<CalVO> cList = session.selectList("CalendarMapper.scheduleList",g_code);
		return cList;
	}

	@Override
	public int insertcalendar(CalVO vo, SqlSession session) {
		int result = session.insert("CalendarMapper.scheduleinsert", vo);
		return result;
	}

	@Override
	public List<CalVO> myscheduleList(String id, SqlSession session) {
		List<CalVO> cMyList = session.selectList("CalendarMapper.myscheduleList",id);
		return cMyList;
	}

	@Override
	public int makecalendar(CalVO vo, SqlSession session) {
		int result = session.insert("CalendarMapper.makeCal", vo);
		return result;
	}

	@Override
	public int deletecalendar(String id, SqlSession session) {
		int result = session.delete("CalendarMapper.deleteCal", id);
		return result;
	}

	@Override
	public GuVO selectgroup(String id, int g_code, SqlSession session) {
		GuVO group = session.selectOne("CalendarMapper.calmygroup", 
			      Map.of("id", id, "g_code", g_code));
		return group;
	}

	@Override
	public int deleteschedule(int calno, SqlSession session) {
		int result = session.delete("CalendarMapper.scheduledelete", calno);
		return result;
	}

	@Override
	public int updateschedule(CalVO vo, SqlSession session) {
		int result = session.update("CalendarMapper.scheduleupdate",vo);
		return result;
	}

	@Override
	public int updatedrag(CalVO vo, SqlSession session) {
		int result = session.update("CalendarMapper.dragupdate", vo);
		return result;
	}

	@Override
	public List<CalVO> listlistcalendar(String userId,SqlSession session) {
		List<CalVO> cList = session.selectList("CalendarMapper.Myschedulelistlist", userId);
		return cList;
	}

	@Override
	public List<CalVO> listprojectcalendar(int projectNo, SqlSession session) {
		List<CalVO> cList = session.selectList("CalendarMapper.projectscheduleList", projectNo);
		return cList;
	}

}
