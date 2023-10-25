package com.kh.msg.calendar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.msg.calendar.domain.GuVO;
import com.kh.msg.calendar.service.guService;
@Service
public class GuImpl implements guService {

	@Override
	public void addMember(GuVO gvo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMember(String id, int g_code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int searchgcode(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countgroup(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer countgu(int g_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMemberG_code(int g_code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GuVO> getNotyetGrouplist(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
