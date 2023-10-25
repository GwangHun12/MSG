package com.kh.msg.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.msg.admin.service.AdminService;
import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.user.domain.User;

@Controller
public class AdminController {

	@Autowired
	private AdminService aService;
	
	@RequestMapping(value = "/admin/removeUser.do", method = RequestMethod.GET)
	public ModelAndView removeUser(ModelAndView mv
			, @RequestParam("userId") String userId) {
		try {
			int result = aService.deleteUser(userId);
			if(result > 0) {
				mv.setViewName("redirect:/admin/userList.do");
			}else {
				mv.addObject("msg", "회원 탈퇴 처리가 완료되지 않았습니다.");
				mv.addObject("error", "회원 탈퇴 처리 실패");
				mv.addObject("url", "/admin/userList.do");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "회원 탈퇴 처리가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/admin/userList.do");
			mv.setViewName("common/serviceFailed");
		}
	    return mv;
	}


	@RequestMapping(value="/admin/userList.do", method=RequestMethod.GET)
	public ModelAndView showUserList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, ModelAndView mv) {
		try {
			Integer totalCount = aService.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<User> uList = aService.selectUserList(pInfo);
			
			if(!uList.isEmpty()) {
				mv.addObject("uList", uList).addObject("pInfo", pInfo).setViewName("admin/userList");
			} else {
				mv.addObject("msg", "회원 전체 조회가 완료되지 않았습니다.");
				mv.addObject("error", "회원 조회 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "회원 조회가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/index.do");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/search.do", method=RequestMethod.GET)
	public String searchUserList(
			@RequestParam("searchCondition") String searchCondition
		, @RequestParam("searchKeyword") String searchKeyword
		, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
		, Model model) {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("searchCondition", searchCondition); 
			paramMap.put("searchKeyword", searchKeyword);
			int totalCount = aService.getListCount(paramMap);
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<User> uList = aService.searchUserByKeyword(pInfo, paramMap);
			if(!uList.isEmpty()) {
				model.addAttribute("searchCondition", searchCondition);
				model.addAttribute("searchKeyword", searchKeyword);
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("uList", uList);
				return "admin/userList";
			} else {
				model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 제목으로 조회 실패");
				model.addAttribute("url", "/list.gg");
				return "common/serviceFailed";
			}
		}
	
	private PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int naviTotalCount;
		naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage);
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		
		return pInfo;
	}
}
