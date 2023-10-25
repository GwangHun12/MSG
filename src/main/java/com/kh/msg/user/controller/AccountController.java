package com.kh.msg.user.controller;


import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*import org.apache.commons.codec.binary.Base64;
*/import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.msg.user.domain.User;
import com.kh.msg.user.service.accountService;

@Controller
public class AccountController {

	@Autowired
	private accountService aService;

	@RequestMapping(value = "/account/search_id.do", method = RequestMethod.GET)
	public String search_id(HttpServletRequest request, Model model, User searchVO) {

		return "/account/search_id.do";
	}

	@RequestMapping(value = "/account/search_pwd.do", method = RequestMethod.GET)
	public String search_pwd(HttpServletRequest request, Model model, User searchVO) {

		return "/account/search_pwd.do";
	}

	@RequestMapping(value = "/account/search_result_id.do")
	public String search_result_id(HttpServletRequest request, Model model,
	    @RequestParam(required = true, value = "userName") String userName, 
	    @RequestParam(required = true, value = "userPhone") String userPhone,
	    User searchVO) {
	 
	 
	try {
	    
	    searchVO.setUserName(userName);
	    searchVO.setUserPhone(userPhone);
	    User memberSearch = aService.memberIdSearch(searchVO);
	    
	    model.addAttribute("searchVO", memberSearch);
	 
	} catch (Exception e) {
	    System.out.println(e.toString());
	    model.addAttribute("msg", "오류가 발생되었습니다.");
	}
	 
	return "/account/search_result_id";
	}
	
	// SELECT * FROM user_TBL WHERE user_ID = ? AND user_PW = ?
	/*
	 * @RequestMapping(value = "/account/search_result_pwd.do", method =
	 * RequestMethod.POST) public String search_result_pwd(HttpServletRequest
	 * request, Model model,
	 * 
	 * @RequestParam(required = true, value = "userName") String userName,
	 * 
	 * @RequestParam(required = true, value = "userPhone") String userPhone,
	 * 
	 * @RequestParam(required = true, value = "userId") String userId, User
	 * searchVO) {
	 * 
	 * try {
	 * 
	 * searchVO.setUserName(userName); searchVO.setUserPhone(userPhone);
	 * searchVO.setUserId(userId); int memberSearch =
	 * aService.memberPwdCheck(searchVO);
	 * 
	 * if(memberSearch == 0) { model.addAttribute("msg",
	 * "기입된 정보가 잘못되었습니다. 다시 입력해주세요."); return "/account/search_pwd"; }
	 * 
	 * String newPwd = RandomStringUtils.randomAlphanumeric(10); String enpassword =
	 * encryptPassword(newPwd); searchVO.setUserPw(enpassword);
	 * 
	 * aService.passwordUpdate(searchVO);
	 * 
	 * model.addAttribute("newPwd", newPwd);
	 * 
	 * } catch (Exception e) { System.out.println(e.toString());
	 * model.addAttribute("msg", "오류가 발생되었습니다."); }
	 * 
	 * 
	 * return "/account/search_result_pwd"; }
	 */
	
	/*
	 * public static String encryptPassword(String data) throws Exception {
	 * 
	 * if (data == null) { return ""; }
	 * 
	 * byte[] plainText = null; // 평문 byte[] hashValue = null; // 해쉬값 plainText =
	 * data.getBytes();
	 * 
	 * MessageDigest md = MessageDigest.getInstance("SHA-256"); hashValue =
	 * md.digest(plainText);
	 * 
	 * return new String(Base64.encodeBase64(hashValue)); }
	 */
	
}
	
	
