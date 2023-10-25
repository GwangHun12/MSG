package com.kh.msg.qa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.msg.qa.domain.QReply;
import com.kh.msg.qa.domain.Qa;
import com.kh.msg.qa.service.QReplyService;
import com.kh.msg.qa.service.QaService;

@Controller
public class QReplyController {

	@Autowired
	private QaService qService;
	@Autowired
	private QReplyService qrService;
//	@Autowired
//	private Qa qa;
	
	@RequestMapping(value="/qreply/add.do", method=RequestMethod.POST)
	public ModelAndView insertQReply(ModelAndView mv
			, @ModelAttribute QReply qreply
			, HttpSession session) {
		String url ="";
		try {
			String qreplyWriter = (String)session.getAttribute("userId");
			qreply.setUserId(qreplyWriter);

			Integer secret = qreply.getSecret();
	        int secretValue = (secret != null) ? secret : 0;
	        qreply.setSecret(secretValue);
	        
			int result = qrService.insertReply(qreply);
			Qa qa = qService.selectQaByNo(qreply.getQaNo());
			url = "/qa/qadetail.do?qaNo="+qreply.getQaNo()+"&password="+qa.getPassword();
			if(result > 0) {
				int qaNo = qreply.getQaNo();
				int commentCount = qrService.getReplyCountByQaNo(qaNo);
				
				Map<String, Integer> replyCountInfo = new HashMap<>();
		        replyCountInfo.put("qaNo", qaNo);
		        replyCountInfo.put("commentCount", commentCount);
				
				result = qService.updateReplyNum(replyCountInfo);
				mv.setViewName("redirect:"+url);
			} else {
				mv.addObject("msg", "댓글 등록이 완료되지 않았습니다.");
				mv.addObject("error", "댓글 등록 실패");
				mv.addObject("url", url);
				mv.setViewName("common/serviceFailed");
				
			}
		} catch (Exception e) {
			mv.addObject("msg", "댓글 등록이 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFailed");
		}

		return mv;
	}
	@RequestMapping(value="/qreply/update.do", method=RequestMethod.POST)
	public ModelAndView updateReply(ModelAndView mv
			, @ModelAttribute QReply qreply
			, HttpSession session) {
		String url ="";
		try {
			String qreplyWriter = (String)session.getAttribute("userId");
			if(qreplyWriter != null && !qreplyWriter.equals("")) {
				qreply.setUserId(qreplyWriter);
				int result = qrService.updateReply(qreply);
				url= "/qa/qadetail.do?qaNo="+qreply.getQaNo();
				if(result > 0) {
					mv.setViewName("redirect:"+url);
				
			} else {
				mv.addObject("msg", "수정이 되지 않았습니다.");
				mv.addObject("error", "정보 수정 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
		} else {
			mv.addObject("msg", "로그인이 되지 않았습니다.");
			mv.addObject("error", "로그인 정보확인 실패");
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/serviceFailed");
		}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의 바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFailed");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/qreply/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteReply(ModelAndView mv
			, @ModelAttribute QReply qreply
			, HttpSession session ) {
		String url = "";
		try {
			String userId = (String)session.getAttribute("userId");
			String qreplyWriter = qreply.getUserId(); 
			url = "/qa/qadetail.do?qaNo="+qreply.getQaNo();
			if(qreplyWriter != null && qreplyWriter.equals(userId)) {
				int result = qrService.deleteReply(qreply);
				if(result > 0) {
					int qaNo = qreply.getQaNo();
					int commentCount = qrService.getReplyCountByQaNo(qaNo);
					
					Map<String, Integer> replyCountInfo = new HashMap<>();
			        replyCountInfo.put("qaNo", qaNo);
			        replyCountInfo.put("commentCount", commentCount);
					
					result = qService.updateReplyNum(replyCountInfo);
					mv.setViewName("redirect:"+url);
				} else {
					mv.addObject("msg", "댓글 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "댓글 삭제 실패");
					mv.addObject("url", url);
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("msg", "자신의 댓글만 삭제할 수 있습니다.");
				mv.addObject("error", "댓글 삭제 불가");
				mv.addObject("url", url);
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의 바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
}
