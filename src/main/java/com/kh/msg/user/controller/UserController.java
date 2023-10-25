package com.kh.msg.user.controller;




import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.msg.user.domain.User;
import com.kh.msg.user.service.UserService;



@Controller
public class UserController {

	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	 

	@Autowired
	private  UserService service;

	@RequestMapping(value = "/user/register2.do", method = RequestMethod.GET)
	public String showRegisterForm() {
		return "/user/register2";
	}

	@RequestMapping(value = "/user/register2.do", method = RequestMethod.POST)
	public String registeruser(
//			@RequestParam("userId") String userId
			@ModelAttribute User user, Model model) {
		try {
			// INSERT INTO user_TBL
			int result = service.insertuser(user);
			if (result > 0) {
				// 성공하면 로그인 페이지
				// home.jsp가 로그인할 수 있는 페이지가 되면 됨!!
				return "redirect:/index.jsp";
			} else {
				// 실패하면 에러페이지로 이동
				model.addAttribute("msg", "회원가입이 완료되지 않았습니다.");
				model.addAttribute("error", "회원가입 실패");
				model.addAttribute("url", "/user/register2.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/user/register2.do");
			return "common/serviceFailed";
		}
	}
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String postRegister(MemberStore vo) throws Exception {
//		int result = service.idChk(vo);
//		try {
//			if( result == 1) {
//				return "/user/register";
//			}else if(result == 0){
//				service.register(vo);
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new RuntimeException();
//		}
//		return "redirect:/";
//	}

	@RequestMapping(value = "/user/update.do", method = RequestMethod.POST)
	public String modifyuser(@ModelAttribute User user, Model model) {
		try {
			String userId = user.getUserId();
			int result = service.updateUser(user);
			if (result > 0) {
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "회원 정보 수정이 완료되지 않았습니다.");
				model.addAttribute("error", "회원정보 수정 실패");
				model.addAttribute("url", "/user/mypage.do?userId" + userId);
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	// 회원 탈퇴 get
	@RequestMapping(value="/user/memberDeleteView.do", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "/user/memberDeleteView";
	}
	
	// 회원 탈퇴 post
	@RequestMapping(value="/user/memberDelete.do", method = RequestMethod.POST)
	public String memberDelete(@ModelAttribute("User") User vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		// 세션에 있는 member를 가져와 member변수에 넣어줍니다.
//		User user = (User) session.getAttribute("user");
		// 세션에있는 비밀번호
		String userId = (String) session.getAttribute("userId");
		User user = service.getUserById(userId);
		// vo로 들어오는 비밀번호
		String voPass = vo.getUserPw();
		
		if(user.getUserPw().equals(voPass)) {
			rttr.addFlashAttribute("msg", false);
			service.memberDelete(vo);
			session.invalidate();
			return "redirect:/user/logout.do";
		}else {
			return null;
		}
	}
		
//	@RequestMapping(value = "/user/delete.do", method = RequestMethod.GET)
//	public String outServiceMember(@RequestParam("userId") 
//					String userId, Model model) {
//		try {
//			int result = service.deleteUser(userId);
//			if (result > 0) {
//				return "redirect:/user/logout.do";
//			} else {
//				model.addAttribute("msg", "회원 탈퇴가 완료되지 않았습니다.");
//				model.addAttribute("error", "회원탈퇴 실패");
//				model.addAttribute("url", "/index.jsp");
//				return "common/serviceFailed";
//			}
//		} catch (Exception e) {
//			model.addAttribute("msg", "관리자에게 문의해주세요.");
//			model.addAttribute("error", e.getMessage());
//			model.addAttribute("url", "/index.jsp");
//			return "common/serviceFailed";
//		}
//	}

	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public String userLoginCheck(@ModelAttribute 
			User user, HttpSession session, Model model) {
		try {
			// SELECT * FROM user_TBL WHERE user_ID = ? AND user_PW = ?
			User mOne = service.checkUserLogin(user);
			if (mOne != null) {
				// 성공하면 메인페이지로 이동
				session.setAttribute("userId", mOne.getUserId());
				session.setAttribute("userNickName", mOne.getUserNickName());
				return "redirect:/index.jsp";
			} else {
				// 실패하면 에러페이지로 이동
				model.addAttribute("msg", "로그인이 완료되지 않았습니다.");
				model.addAttribute("error", "로그인 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}

//			int result = service.checkMemberLogin(member);
//			if(result > 0) {
//				// 성공하면 메인페이지로 이동
//				session.setAttribute("memberId", member.getMemberId());
//				return "redirect:/index.jsp";
//			}else {
//				// 실패하면 에러페이지로 이동
//				model.addAttribute("msg", "로그인이 완료되지 않았습니다.");
//				model.addAttribute("error", "로그인 실패");
//				model.addAttribute("url", "/index.jsp");
//				return "common/errorPage";
//			}			
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/user/register2.do");
			return "common/serviceFailed";
		}
	}

	@RequestMapping(value = "/user/logout.do", method = RequestMethod.GET)
	public String userLogout(HttpSession session, Model model) {
		if (session != null) {
			session.invalidate();
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg", "로그아웃을 완료하지 못하였습니다.");
			model.addAttribute("error", "로그아웃 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFailed ";
		}
	}

	@RequestMapping(value = "/user/mypage.do", method = RequestMethod.GET)
	public String showMyPage(
			// 쿼리스트링 받기 위해서 RequestParam 써줌
			@RequestParam("userId") String userId
			// 모델에 키와 값으로 데이터를 넣어주면 jsp에서 꺼내서 사용가능
			, Model model) {
		// SELECT * FROM MEMBER_TBL WHERE MEBER_ID = ?
		// Exception발생 시 에러메시지를 출력하기 위해 try ~ catch 설정해줌
		try {
			User user = service.getUserById(userId);
			if (user != null) {
				model.addAttribute("user", user);
				return "user/mypage";
			} else {
				model.addAttribute("msg", "회원 정보 조회를 완료하지 못하였습니다.");
				model.addAttribute("error", "마이페이지 조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	@RequestMapping(value = "/user/update.do", method = RequestMethod.GET)
	public String showModifyForm(String userId, Model model) {
		User user = service.getUserById(userId);
		model.addAttribute("user", user);
		return "user/modify";
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/user/idChk.do", method = RequestMethod.POST) public
	 * int idChk(UserStore vo) throws Exception { int result = service.idChk(vo);
	 * return result; }
	 */
	// 아이디 중복 검사
		@RequestMapping(value = "/user/userIdChk.do", method = RequestMethod.POST)
		@ResponseBody
		public String memberIdChkPOST(String userId) throws Exception{
			
			/* System.out.println("userIdChk() 진입"); */
			logger.info("userIdChk() 진입");
			
			int result = service.idCheck(userId);
			
			logger.info("결과값 = " + result);
			
			if(result != 0) {
				
				return "fail";	// 중복 아이디가 존재
				
			} else {
				
				return "success";	// 중복 아이디 x
				
			}	
			
		} // memberIdChkPOST() 종료	
	
	

	/* 이메일 인증 */

	@RequestMapping(value = "/mailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public void mailCheckGET(String email) throws Exception {

		/* 뷰(View)로부터 넘어온 데이터 확인 */

		
		 
		 logger.info("이메일 데이터 전송 확인"); 
		 logger.info("인증번호 : " + email);
		 

	}

}
