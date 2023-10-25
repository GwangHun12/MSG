package com.kh.msg.calendar.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.calendar.domain.CalVO;
import com.kh.msg.calendar.domain.GuVO;
import com.kh.msg.calendar.service.calService;
import com.kh.msg.calendar.store.CalStore;
@Service
public class calImpl implements calService{
@Autowired
 private CalStore calstore;
@Autowired
 private SqlSession session;
	@Override
	public int makeCal(CalVO vo) {
		int result = calstore.makecalendar(vo,session);
		return result;
	}

	@Override
	public int deleteCal(String id) {
		int result = calstore.deletecalendar(id,session);
		return result;
	}

	@Override
	public GuVO calmygroup(String id, int g_code) {
		GuVO gu = calstore.selectgroup(id,g_code,session);
		return gu;
	}

	@Override
	public List<CalVO> scheduleList(Integer g_code) {
		List<CalVO>cList = calstore.listCalendar(g_code,session);
		return cList;
	}

	@Override
	public List<CalVO> myscheduleList(String id) {
		List<CalVO> cmyList = calstore.myscheduleList(id,session);
		return cmyList;
	}

	@Override
	public int scheduleinsert(CalVO vo) {
		int result = calstore.insertcalendar(vo, session);
		return result;
		
	}

	@Override
	public int scheduleupdate(CalVO vo) {
		int result = calstore.updateschedule(vo,session);
		return result;
		
	}

	@Override
	public int dragupdate(CalVO vo) {
		int result = calstore.updatedrag(vo,session);
		return result;
		
	}

	@Override
	public int scheduledelete(int calno) {
		int result = calstore.deleteschedule(calno, session);
		return result;
	}

	@Override
	public List<CalVO> mymineproject(String userId) {
		List<CalVO> cList = calstore.listlistcalendar(userId, session);
		return cList;
	}

	@Override
	public List<CalVO> projectproject(int projectNo) {
		List<CalVO> cList = calstore.listprojectcalendar(projectNo,session);
		return cList;
	}

}
