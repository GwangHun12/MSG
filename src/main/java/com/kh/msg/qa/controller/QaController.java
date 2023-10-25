package com.kh.msg.qa.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.msg.qa.domain.PageInfo;
import com.kh.msg.qa.domain.Qa;
import com.kh.msg.qa.domain.QReply;
import com.kh.msg.qa.service.QReplyService;
import com.kh.msg.qa.service.QaService;

@Controller
public class QaController {

	@Autowired
	private QaService qService;
	@Autowired
	private QReplyService qrService;

	@RequestMapping(value="/qa/qawrite.do", method=RequestMethod.GET)
	public ModelAndView showWriteForm(ModelAndView mv) {
		
		mv.setViewName("qa/qawrite");
		return mv;
	}
	
	@RequestMapping(value="/qa/qawrite.do", method=RequestMethod.POST)
	public ModelAndView qaRegister(
	    ModelAndView mv,
	    @ModelAttribute Qa qa,
	    HttpSession session,
	    HttpServletRequest request) {
	    try {
	        String userId = (String) session.getAttribute("userId");
	        if (userId != null && !userId.equals("")) {
	            qa.setUserId(userId);
	            
	            // 비밀번호를 int로 변환하여 설정
//	            int password = Integer.parseInt(request.getParameter("password"));
//	            qa.setPassword(password);
	            String password = request.getParameter("password");
	            qa.setPassword(password);
	            
	            int result = qService.insertQa(qa);
	            if (result > 0) {
	                mv.setViewName("redirect:/qa/qalist.do");
	            }
	        } else {
	            mv.addObject("msg", "로그인이 필요합니다.");
	            mv.addObject("error", "로그인이 필요합니다.");
	            mv.addObject("url", "/index.jsp");
	            mv.setViewName("qa/qalist");
	        }
	    } catch (Exception e) {
	        mv.addObject("msg", "질문 등록이 완료되지 않았습니다.");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/qa/qreplyWriter");
	        mv.setViewName("qa/qalist");
	    }
	    return mv;
	}

	
//	@RequestMapping(value="/qa/qawrite.do", method=RequestMethod.POST)
//	public ModelAndView qaRegister(
//	    ModelAndView mv,
//	    @ModelAttribute Qa qa,
//	    HttpSession session,
//	    HttpServletRequest request) {
//	    try {
//	        String userId = (String) session.getAttribute("userId");
//	        if (userId != null && !userId.equals("")) {
//	            qa.setUserId(userId);
//	            
//	            String passwordParameter = request.getParameter("password");
//	            int password = 0; // 기본값 설정
//
//	            if (passwordParameter != null && !passwordParameter.isEmpty()) {
//	                try {
//	                    password = Integer.parseInt(passwordParameter);
//	                } catch (NumberFormatException e) {
//	                    // 정수로 변환할 수 없는 경우 예외 처리
//	                    // 적절한 오류 처리 로직을 추가하세요.
//	                }
//	            }
//	            
//	            qa.setPassword(password);
//
//	            int result = qService.insertQa(qa);
//	            if (result > 0) {
//	                mv.setViewName("redirect:/qa/qalist.do");
//	            }
//	        } else {
//	            mv.addObject("msg", "로그인이 필요합니다.");
//	            mv.addObject("error", "로그인이 필요합니다.");
//	            mv.addObject("url", "/index.jsp");
//	            mv.setViewName("common/serviceFailed");
//	        }
//	    } catch (Exception e) {
//	        mv.addObject("msg", "질문 등록이 완료되지 않았습니다.");
//	        mv.addObject("error", e.getMessage());
//	        mv.addObject("url", "/qa/qreplyWriter");
//	        mv.setViewName("common/serviceFailed");
//	    }
//	    return mv;
//	}



//	@RequestMapping(value="/qa/qalist.do", method=RequestMethod.GET)
//	public ModelAndView showQaList(
//			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
//			, ModelAndView mv) {
//		try {
//			Integer totalCount = qService.getListCount();
//			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
//			List<Qa> qList = qService.selectQaList(pInfo);
//			if(!qList.isEmpty()) {
//				mv.addObject("qList", qList).addObject("pInfo", pInfo).setViewName("qa/qalist");
//			} else {
//				mv.addObject("msg", "게시글 전체 조회가 완료되지 않았습니다.");
//				mv.addObject("error", "게시글 조회 실패");
//				mv.addObject("url", "/qa/qalist.do");
//				mv.setViewName("common/serviceFailed");
//			}
//		} catch (Exception e) {
//			mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
//			mv.addObject("error", e.getMessage());
//			mv.addObject("url", "/qa/qalist.do");
//			mv.setViewName("common/serviceFailed");
//		}
//		return mv;
//	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value="/qa/qalist.do", method=RequestMethod.GET)
	public ModelAndView showQaList(
	        @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage,
	        @RequestParam(value="password", required=false) String enteredPassword,  // 비밀번호 파라미터 추가
	        ModelAndView mv) {
	    try {
	        Integer totalCount = qService.getListCount();
	        PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
	        List<Qa> qList = qService.selectQaList(pInfo);

	        if (!qList.isEmpty()) {
	            // 비밀번호가 입력되었고, 비밀번호가 일치하는 경우에만 상세 페이지로 이동
	            if (enteredPassword != null && !enteredPassword.isEmpty()) {
	                for (Qa qa : qList) {
	                    if (qa.getQaSecret() == 1 && enteredPassword.equals(qa.getPassword())) {
	                        mv.addObject("qList", qList).addObject("pInfo", pInfo).addObject("qa",qa).setViewName("qa/qalist");
	                        return mv;
	                    }
	                }
	                // 비밀번호가 일치하지 않으면 에러 메시지 설정
//	                mv.addObject("errorMsg", "비밀번호가 일치하지 않습니다.");
	            } else {
	                mv.addObject("qList", qList).addObject("pInfo", pInfo).setViewName("qa/qalist");
	            }
	        } else {
	            mv.addObject("msg", "게시글 전체 조회가 완료되지 않았습니다.");
	            mv.addObject("error", "게시글 조회 실패");
	            mv.addObject("url", "/qa/qalist.do");
	            mv.setViewName("qa/qalist");
	        }
	    } catch (Exception e) {
	        mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/qa/qalist.do");
	        mv.setViewName("common/serviceFailed");
	    }
	    return mv;
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

//	@RequestMapping(value="/qa/qadetail.do", method=RequestMethod.GET)
//	public ModelAndView showQaDetail(ModelAndView mv
//			, @RequestParam("qaNo") Integer qaNo) {
//		try {
//			Qa qaOne = qService.selectQaByNo(qaNo);
//			if(qaOne != null) {
//				List<QReply> qrList = qrService.selectQReplyList(qaNo);
//				if(qrList.size() > 0) {
//					mv.addObject("qrList", qrList);
//				}
//				mv.addObject("qa", qaOne);
//				mv.setViewName("qa/qadetail");
//			} else {
//				mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
//				mv.addObject("error", "게시글 상세 조회 실패");
//				mv.addObject("url", "/qa/qalist.do");
//				mv.setViewName("common/serviceFailed");
//			}
//		} catch (Exception e) {
//			mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
//			mv.addObject("error", e.getMessage());
//			mv.addObject("url", "/qa/qalist");
//			mv.setViewName("common/serviceFailed");
//		}
//		return mv;
//	}
	
//	@SuppressWarnings("unlikely-arg-type")
//	@RequestMapping(value = "/qa/qadetail.do", method = RequestMethod.GET)
//	public ModelAndView showQaDetail(ModelAndView mv
//			, @RequestParam("qaNo") Integer qaNo
//			, @RequestParam(value = "password", required = false) String enteredPassword
//			, HttpSession session) {
//	    try {
//	        Qa qaOne = qService.selectQaByNo(qaNo);
//	        if (qaOne != null) {
//	            if (qaOne.getQaSecret() == 1) { // Check qaSecret value
//	                // qaSecret is 1, so it's a secret post
//	                if (enteredPassword == null || !enteredPassword.equals(qaOne.getPassword()+"")) {
//	                    // Password is incorrect or not provided, show serviceFailed page
//	                    mv.addObject("msg", "비밀번호가 일치하지 않습니다.");
//	                    mv.addObject("error", "비밀글 상세 조회 실패");
//	                    mv.addObject("url", "/qa/qalist.do");
//	                    mv.setViewName("common/serviceFailed");
//	                } else {
//	                    // Password is correct, show qadetail page
//	                	List<QReply> qrList = qrService.selectQReplyList(qaNo);
//	                    if (qrList.size() > 0) {
//	                        for (QReply qreply : qrList) {
//	                            if (qreply.getSecret() == 1 && !qreply.getUserId().equals(session.getAttribute("userId").toString())) {
//	                                qreply.setReplyContent("[비밀 댓글]");
//	                            }
//	                        }
//	                        mv.addObject("qrList", qrList);
//	                    }
////	                    List<QReply> qrList = qrService.selectQReplyList(qaNo);
////	                    if (qrList.size() > 0) {
////	                        mv.addObject("qrList", qrList);
////	                    }
////	                    for (QReply qreply : qrList) {
////	                        if (qreply.getSecret() == 1 && !qreply.getUserId().equals(sessionScope.userId)) {
////	                            qreply.setReplyContent("[비밀 댓글]");
////	                        }
////	                    }
//	                    mv.addObject("qa", qaOne);
//	                    mv.setViewName("qa/qadetail");
//	                }
//	            } else {
//	                // It's a public post, show qadetail page
//	                List<QReply> qrList = qrService.selectQReplyList(qaNo);
//	                if (qrList.size() > 0) {
//	                    mv.addObject("qrList", qrList);
//	                }
//	                mv.addObject("qa", qaOne);
//	                mv.setViewName("qa/qadetail");
//	            }
//	        } else {
//	            mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
//	            mv.addObject("error", "게시글 상세 조회 실패");
//	            mv.addObject("url", "/qa/qalist.do");
//	            mv.setViewName("common/serviceFailed");
//	        }
//	    } catch (Exception e) {
//	        mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
//	        mv.addObject("error", e.getMessage());
//	        mv.addObject("url", "/qa/qalist.do");
//	        mv.setViewName("common/serviceFailed");
//	    }
//	    return mv;
//	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/qa/qadetail.do", method = RequestMethod.GET)
	public ModelAndView showQaDetail(ModelAndView mv,
	        @RequestParam("qaNo") Integer qaNo,
	        @RequestParam(value = "password", required = false) String enteredPassword,
	        HttpSession session) {
	    try {
	        Qa qaOne = qService.selectQaByNo(qaNo);
	        if (qaOne != null) {
	            if (qaOne.getQaSecret() == 1) { // Check qaSecret value
	                // qaSecret is 1, so it's a secret post
	                if (enteredPassword == null || !enteredPassword.equals(qaOne.getPassword() + "")) {
	                    // Password is incorrect or not provided, show serviceFailed page
//	                    mv.addObject("msg", "비밀번호가 일치하지 않습니다.");
//	                    mv.addObject("error", "비밀글 상세 조회 실패");
//	                    mv.addObject("url", "/qa/qalist.do");
//	                    mv.setViewName("common/serviceFailed");
//	                    mv.setViewName("redirect:/qa/qalist.do");
	                    String redirectUrl = "/qa/qalist.do?msg=" + URLEncoder.encode("비밀번호가 일치하지 않습니다.", "UTF-8") + "&error=" + URLEncoder.encode("비밀글 상세 조회 실패", "UTF-8");
	                    mv.setViewName("redirect:" + redirectUrl);
	                } else {
	                    // Password is correct, show qadetail page
	                    List<QReply> qrList = qrService.selectQReplyList(qaNo);
	                    int result = qService.updateViewCount(qaNo); // Update the view count and get the result
	                    if (qrList.size() > 0) {
	                        for (QReply qreply : qrList) {
	                            if (qreply.getSecret() == 1 && !qreply.getUserId().equals(session.getAttribute("userId").toString())) {
	                                qreply.setReplyContent("[비밀 댓글]");
	                            }
	                        }
	                        mv.addObject("qrList", qrList);
	                    }
	                    mv.addObject("qa", qaOne);
	                    mv.addObject("updateResult", result); // Add the result to the model
	                    mv.setViewName("qa/qadetail");
	                }
	            } else {
	                // It's a public post, show qadetail page
	                List<QReply> qrList = qrService.selectQReplyList(qaNo);
	                int result = qService.updateViewCount(qaNo);
	                if (qrList.size() > 0) {
	                    for (QReply qreply : qrList) {
	                        if (qreply.getSecret() == 1 && !qreply.getUserId().equals(session.getAttribute("userId").toString())) {
	                            qreply.setReplyContent("[비밀 댓글]");
	                        }
	                    }
	                    mv.addObject("qrList", qrList);
	                }
	                // Increment the view count
	                mv.addObject("qa", qaOne);
	                mv.addObject("updateResult", result);
	                mv.setViewName("qa/qadetail");
	            }
	        } else {
	            mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
	            mv.addObject("error", "게시글 상세 조회 실패");
	            mv.addObject("url", "/qa/qalist.do");
	            mv.setViewName("common/serviceFailed");
	        }
	    } catch (Exception e) {
	        mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/qa/qalist.do");
	        mv.setViewName("common/serviceFailed");
	    }
	    return mv;
	}




	@RequestMapping(value="/qa/qadelete.do", method=RequestMethod.GET)
	public ModelAndView deleteQa(ModelAndView mv
			, @RequestParam("qaNo") Integer qaNo
			, @RequestParam("userId") String userId
			, HttpSession session) {
		try {
			String writeuserId = (String)session.getAttribute("userId");
			System.out.println("userId" + userId);
			System.out.println("writeuserId" + writeuserId);
			Qa qa = new Qa();
			qa.setQaNo(qaNo);
			qa.setUserId(writeuserId);
			if(writeuserId != null && writeuserId.equals(userId)) {
				int result = qService.deleteQa(qa);
				if(result > 0) {
					mv.setViewName("redirect:/qa/qalist.do");
				} else {
					mv.addObject("msg", "문의글 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "게시글 삭제 실패");
					mv.addObject("url", "/qa/qalist.do");
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
				mv.addObject("error", "게시글 삭제 불가");
				mv.addObject("url", "/qa/qalist.do");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/qa/qalist.do");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	@RequestMapping(value="/qa/qamodify.do", method=RequestMethod.GET)
	public ModelAndView showModifyForm(ModelAndView mv
			, @RequestParam("qaNo") Integer qaNo
			, HttpSession session) {
		try {
	        String userId = (String) session.getAttribute("userId");
	        Qa qa = qService.selectQaByNo(qaNo);
	        
	        if (userId != null && userId.equals(qa.getUserId())) {
	            mv.addObject("qa", qa);
	            mv.setViewName("qa/qamodify");
	        } else {
	            // 디버깅 메시지 추가
	            System.out.println("userId: " + userId);
	            System.out.println("qa.getUserId(): " + qa.getUserId());
	            
	            mv.addObject("msg", "게시글 수정 권한이 없습니다.");
	            mv.addObject("error", "게시글 수정 실패");
	            mv.addObject("url", "/qa/qadetail.do?qaNo=" + qaNo);
	            mv.setViewName("common/serviceFailed");
	        }
	    } catch (Exception e) {
	        mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
	        mv.addObject("error", e.getMessage());
	        mv.addObject("url", "/qa/qalist.do");
	        mv.setViewName("common/serviceFailed");
	    }
	    return mv;
	}
	
	@RequestMapping(value="/qa/qamodify.do", method=RequestMethod.POST)
	public ModelAndView QaModify(ModelAndView mv
			, @ModelAttribute Qa qa
			, HttpSession session
			, HttpServletRequest request) {
		try {
			System.out.println("qa : " + qa);
			String userId = (String)session.getAttribute("userId");
			String writeuserId = qa.getUserId();
			if(writeuserId != null && writeuserId.equals(userId)) {
			int result = qService.updateQa(qa);
			if(result > 0) {
				mv.setViewName("redirect:/qa/qadetail.do?qaNo="+qa.getQaNo()+"&password="+qa.getPassword());
			} else {
				mv.addObject("msg", "게시글 수정이 완료되지 않았습니다.");
				mv.addObject("error", "게시글 수정 실패");
				mv.addObject("url", "/qa/qamodify.do?qaNo="+qa.getQaNo());
				mv.setViewName("common/serviceFailed");
			}
			} else {
				mv.addObject("msg", "게시글 수정 권한이 없습니다.");
				mv.addObject("error", "게시글 수정 실패");
				mv.addObject("url", "/qa/qamodify.do?qaNo="+qa.getQaNo());
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/qa/qamodify.do?qaNo="+qa.getQaNo());
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	@RequestMapping(value="/qa/qasearch.do", method=RequestMethod.GET)
	public String searchQaList(
			@RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchKeyword") String searchKeyword
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, Model model) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("searchCondition", searchCondition);
				paramMap.put("searchKeyword", searchKeyword);
				int totalCount = qService.getListCount();
				PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Qa> searchList = qService.searchQaByKeyword(pInfo, paramMap);
		if(!searchList.isEmpty()) {
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("qList", searchList);
			return "qa/qalist";
		} else {
			model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "질문 제목으로 조회 실패");
			model.addAttribute("url", "/qa/qalist.do");
			return "common/serviceFailed";
		}
			
	}
//	
//	@RequestMapping(value="/qa/modify.do", method=RequestMethod.GET)
//	@PreAuthorize("hasRole('ROLE_MEMBER') || hasRole('ROLE_ADMIN')")
//	public String QaModify(@RequestParam int qaNo, @ModelAttribute("qa") Qa qa
//			, Model model) {
//		
//		return null;
//	}
}
