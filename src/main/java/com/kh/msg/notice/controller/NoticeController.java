package com.kh.msg.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.msg.notice.domain.Notice;
import com.kh.msg.notice.domain.PageInfo;
import com.kh.msg.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;
	
	@RequestMapping(value="/notice/write.do", method=RequestMethod.GET)
	public ModelAndView showWriteForm(ModelAndView mv) {

		mv.setViewName("notice/write");
		return mv;
	}
	
	@RequestMapping(value="/notice/write.do", method=RequestMethod.POST)
	public ModelAndView Register(
			ModelAndView mv
			, @ModelAttribute Notice notice
//			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
//			, HttpSession session
			, HttpServletRequest request) {
		try {
//			String userId = (String)session.getAttribute("userId");
//			if(userId != null && !userId.equals("")) {
//				notice.setUserId(userId);
//				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					// 파일정보(이름, 리네임, 경로, 크기) 및 파일저장
//					Map<String, Object> rMap = this.saveFile(request, uploadFile);
//					notice.setNoticeFileName((String)rMap.get("fileName"));
//					notice.setNoticeFileRename((String)rMap.get("fileRename"));
//					notice.setNoticeFilePath((String)rMap.get("filePath"));
//					notice.setNoticeFileLength((long)rMap.get("fileLength"));
//				}
				int result = nService.insertNotice(notice);
				mv.setViewName("redirect:/notice/list.do");
//			} else {
//				mv.addObject("msg", "로그인이 필요합니다.");
//				mv.addObject("error", "로그인이 필요합니다.");
//				mv.addObject("url", "/index.jsp");
//				mv.setViewName("common/serviceFailed");
//			}
		} catch (Exception e) {
			mv.addObject("msg", "댓글 등록이 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/notice/write.do");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
//	public Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
//		Map<String, Object> fileMap = new HashMap<String, Object>();
//		// resources 경로 구하기
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		// 파일 저장경로 구하기
//		String savePath = root + "\\nuploadFiles";
//		// 파일 이름 구하기
//		String fileName = uploadFile.getOriginalFilename();
//		// 파일 확장자 구하기
//		String extension = 
//				fileName.substring(uploadFile.getOriginalFilename().lastIndexOf(".")+1);
//		// 시간으로 파일 리네임하기
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String fileRename = sdf.format(new Date(System.currentTimeMillis()))+"."+extension;
//		// 파일 저장 전 폴더 만들기
//		File saveFolder = new File(savePath);
//		if(!saveFolder.exists()) {
//			saveFolder.mkdir();
//		}
//		// 파일 저장
//		File saveFile = new File(savePath+"\\"+fileRename);
//		uploadFile.transferTo(saveFile);
//		long fileLength = uploadFile.getSize();
//		// 파일 정보 리턴
//		fileMap.put("fileName", fileName);
//		fileMap.put("fileRename", fileRename);
//		fileMap.put("filePath", "../resources/nuploadFiles/"+fileRename);
//		fileMap.put("fileLength", fileLength);
//		return fileMap;
//	}
	
	@RequestMapping(value="/notice/list.do", method=RequestMethod.GET)
	public ModelAndView showNoticeList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, ModelAndView mv) {
		try {
			Integer totalCount = nService.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = nService.selectNoticeList(pInfo);
			if(!nList.isEmpty()) {
				mv.addObject("nList", nList).addObject("pInfo", pInfo).setViewName("notice/list");
			} else {
				mv.addObject("msg", "공지사항 전체 조회가 완료되지 않았습니다.");
				mv.addObject("error", "공지사항 조회 실패");
				mv.addObject("url", "/notice/list.do");
				mv.setViewName("notice/list");
			}
		} catch (Exception e) {
			mv.addObject("msg", "공지사항 조회가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/notice/list.do");
			mv.setViewName("notice/list");
		}
		return mv;
	}
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
	public ModelAndView showNoticeDetail(ModelAndView mv
			, @RequestParam("noticeNo") Integer noticeNo) {
		try {
			Notice noticeOne = nService.selectNoticeByNo(noticeNo);
			if(noticeOne != null) {
					mv.addObject("notice", noticeOne);
					mv.setViewName("notice/detail");
			} else {
				mv.addObject("msg", "공지사항 조회가 완료되지 않았습니다.");
				mv.addObject("error", "공지사항 상세 조회 실패");
				mv.addObject("url", "/notice/list.do");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "공지사항 조회가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/notice/list.do");
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
	
	@RequestMapping(value="/notice/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteNotice(ModelAndView mv
			, @RequestParam("noticeNo") Integer noticeNo
			, HttpSession session) {
		try {
//			String userId = (String)session.getAttribute("userId");
			Notice notice = new Notice();
			notice.setNoticeNo(noticeNo);
//			notice.setUserId(userId);
//			if(userId != null && userId.equals(userId)) {
				int result = nService.deleteNotice(notice);
				if(result > 0) {
					mv.setViewName("redirect:/notice/list.do");
				} else {
					mv.addObject("msg", "공지사항 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "공지사항 삭제 실패");
					mv.addObject("url", "/notice/list.do");
					mv.setViewName("common/serviceFailed");
//				}
//			} else {
//				mv.addObject("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
//				mv.addObject("error", "공지사항 삭제 불가");
//				mv.addObject("url", "/notice/list.do");
//				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/notice/list.do");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	@RequestMapping(value="/notice/modify.do", method=RequestMethod.GET)
	public ModelAndView showModifyForm(ModelAndView mv
			, @RequestParam("noticeNo") Integer noticeNo) {
		try {
			Notice notice = nService.selectNoticeByNo(noticeNo);
			mv.addObject("notice", notice);
			mv.setViewName("notice/modify");
		} catch (Exception e) {
			mv.addObject("msg", "공지사항 조회가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/notice/detail.do?noticeNo="+noticeNo);
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	@RequestMapping(value="/notice/modify.do", method=RequestMethod.POST)
	public ModelAndView NoticeModify(ModelAndView mv
			, @ModelAttribute Notice notice
			, HttpSession session
			, HttpServletRequest request) {
		try {
			int result = nService.updateNotice(notice);
			if(result > 0) {
				mv.setViewName("redirect:/notice/detail.do?noticeNo="+notice.getNoticeNo());
			} else {
				mv.addObject("msg", "공지사항 수정이 완료되지 않았습니다.");
				mv.addObject("error", "공지사항 수정 실패");
				mv.addObject("url", "/notice/modify.do?noticeNo="+notice.getNoticeNo());
				mv.setViewName("common/serviceFailed");
			}
//			} else {
//				mv.addObject("msg", "공지사항 수정 권한이 없습니다.");
//				mv.addObject("error", "공지사항 수정 실패");
//				mv.addObject("url", "/notice/modify.do?noticeNo="+notice.getNoticeNo());
//				mv.setViewName("common/serviceFailed");
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/notice/modify.do?noticeNo="+notice.getNoticeNo());
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}

		
	@RequestMapping(value="/notice/search.do", method=RequestMethod.GET)
	public String searchNoticeList(
			@RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchKeyword") String searchKeyword
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, Model model) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("searchCondition", searchCondition);
				paramMap.put("searchKeyword", searchKeyword);
				int totalCount = nService.getListCount();
				PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Notice> searchList = nService.searchNoticeByKeyword(pInfo, paramMap);
		if(!searchList.isEmpty()) {
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("nList", searchList);
			return "notice/list";
		} else {
			model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "공지사항 제목으로 조회 실패");
			model.addAttribute("url", "/notice/list.do");
			return "common/serviceFailed";
		}
			
	}
}
