package com.kh.msg.project.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.msg.calendar.domain.CalVO;
import com.kh.msg.calendar.service.calService;
import com.kh.msg.notice.domain.Notice;
import com.kh.msg.notice.service.NoticeService;
import com.kh.msg.project.domain.Project;
import com.kh.msg.project.domain.SideProject;
import com.kh.msg.project.domain.SideProjectBoard;
import com.kh.msg.project.service.ProjectService;
import com.kh.msg.user.domain.User;
import com.kh.msg.user.service.UserService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService pService;
	@Autowired
	private UserService uService;
	@Autowired
	private calService cService;
	@Autowired
	private NoticeService nService;
	
	@RequestMapping(value="/project/my.do", method=RequestMethod.GET)
	public ModelAndView showMyProjectPage(
			ModelAndView mv
			, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		Project project = new Project();
		project.setProjectNo(0);
		List<Project> pList = pService.getProjectById(userId);
		List<CalVO> cList = cService.mymineproject(userId);
		List<Notice> nList = nService.allNoticeList();
		
		if(userId != null) {
			mv.addObject("nList", nList);
			mv.addObject("pList", pList);
			mv.addObject("cList",cList);
			mv.addObject(project);
			mv.setViewName("project/myProject");			
		} else {
			mv.setViewName("user/login");
		}
		return mv;
	}
	
	@RequestMapping(value="/project/main.do", method=RequestMethod.GET)
	public ModelAndView showMainProjectPage(ModelAndView mv
			, @RequestParam("projectNo") Integer projectNo
			, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		Project sendProject = new Project();
		List<Project> pList = pService.getProjectById(userId);
		sendProject.setProjectNo(projectNo);
		sendProject.setProjectMember(userId);
		List<Notice> nList = nService.allNoticeList();
		
		List<CalVO> cList = new ArrayList<CalVO>();
		 for (Project project : pList) { project.getProjectNo(); 
				 cList = cService.projectproject(project.getProjectNo());		  
		  }
		 
		Project pOne = pService.getProjectByNo(sendProject);
		Project project = new Project(projectNo, pOne.getProjectName(), pOne.getProjectCreator());
		List<SideProject> spList = pService.getSideProjectByNo(projectNo);
		mv.addObject("nList", nList);
		mv.addObject("spList", spList);
		mv.addObject("project", project);
		mv.addObject("cList",cList);
		mv.setViewName("project/mainProject");
		return mv;
	}
	
	@RequestMapping(value="/project/side.do", method=RequestMethod.GET)
	public ModelAndView showSideProjectPage(ModelAndView mv
			, @RequestParam("sideProjectNo") int sproNo
			, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		SideProject spOne = pService.getSideProjectBySproNo(sproNo);
		Project sendProject = new Project();
		sendProject.setProjectNo(spOne.getSproProjectNo());
		sendProject.setProjectMember(userId);
		Project pOne = pService.getProjectByNo(sendProject);
		List<SideProject> spList = pService.getSideProjectByNo(pOne.getProjectNo());
		List<SideProjectBoard> spbList = pService.getSideProjectBoardList(sproNo);
		mv.addObject("spbList", spbList);
		mv.addObject("spList", spList);
		mv.addObject("pOne", pOne);
		mv.addObject("spOne", spOne);
		mv.setViewName("project/sideProject");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/project/spBoardList.do", produces="application/json;charset=utf-8", method=RequestMethod.GET)
	public String getBoardList(@RequestParam("sproNo") int sproNo
			, HttpSession session) {
		List<SideProjectBoard> spbList = pService.getSideProjectBoardList(sproNo);
		String userId = (String)session.getAttribute("userId");
		User uOne = uService.getUserById(userId);
		String userNickName = uOne.getUserNickName();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("userNickName", userNickName);
		resultMap.put("spbList", spbList);
		Gson gson = new Gson();
		
		return gson.toJson(resultMap);
	}
	
	@RequestMapping(value="/project/createProject.do", method=RequestMethod.GET)
	public ModelAndView addProject(ModelAndView mv
			,HttpSession session
			, @RequestParam("projectName") String projectName) {
		String userId = (String)session.getAttribute("userId");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);
		paramMap.put("projectName", projectName);
		int result = pService.addProject(paramMap);
		if(result > 0) {
			List<Project> pList = pService.getProjectById(userId);
			mv.addObject("pList", pList);
			mv.setViewName("redirect:/project/my.do");
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/project/createSideProject.do", method=RequestMethod.GET)
	public ModelAndView addSideProject(ModelAndView mv
			, @RequestParam("projectNo") int sproProjectNo
			, @RequestParam("sproName") String sproName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sproName", sproName);
		paramMap.put("sproProjectNo", sproProjectNo);
		int result = pService.addSideProject(paramMap);
		if(result > 0) {
			List<SideProject> spList = pService.getSideProjectByNo(sproProjectNo);
			mv.addObject("spList", spList);
			mv.setViewName("redirect:/project/main.do?projectNo="+sproProjectNo);
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/project/customSideProject.do", method=RequestMethod.GET)
	public ModelAndView modifySideProject(ModelAndView mv
			, @RequestParam("projectNo") int sproProjectNo
			, @RequestParam("sproName") String sproName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sproName", sproName);
		paramMap.put("sproProjectNo", sproProjectNo);
		int result = pService.modifySideProject(paramMap);
		if(result > 0) {
			List<SideProject> spList = pService.getSideProjectByNo(sproProjectNo);
			mv.addObject("spList", spList);
			mv.setViewName("redirect:/project/main.do?projectNo="+sproProjectNo);
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/project/createBoard.do", method=RequestMethod.POST)
	public String addBoard(HttpSession session
			, @RequestParam("sproNo") int sproNo
			, @RequestParam("spbContent") String spbContent) {
		String userId = (String)session.getAttribute("userId");
		User uOne = uService.getUserById(userId);
		String spbWriter = uOne.getUserNickName();
		SideProjectBoard spBoard = new SideProjectBoard(spbContent, spbWriter, sproNo);
		Integer result = pService.addBoard(spBoard);
		return String.valueOf(result);
	}
	
	@RequestMapping(value="/project/inviteUser.do", method=RequestMethod.GET)
	public ModelAndView inviteUser(ModelAndView mv
			, @RequestParam("projectNo") int projectNo
			, @RequestParam("projectName") String projectName
			, @RequestParam("projectCreator") String projectCreator
			, @RequestParam("projectMember") String projectMember) {
		Project project = new Project(projectNo, projectName, projectCreator, projectMember);
		int result = pService.inviteUser(project);
		if(result > 0) {
			mv.setViewName("redirect:/project/my.do");
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/project/modifyProject.do", method=RequestMethod.GET)
	public ModelAndView modifyProject(ModelAndView mv
			, @RequestParam("projectNo") int projectNo
			, @RequestParam("projectName") String projectName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectName", projectName);
		paramMap.put("projectNo", projectNo);
		int result = pService.modifyProject(paramMap);
		if(result > 0) {
			mv.setViewName("redirect:/project/main.do?projectNo=" + projectNo);
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/project/leaveProject.do", method=RequestMethod.GET)
	public ModelAndView leaveProject(ModelAndView mv
			, @RequestParam("projectNo") int projectNo
			, @RequestParam("projectMember") String projectMember) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectMember", projectMember);
		paramMap.put("projectNo", projectNo);
		int result = pService.leaveProject(paramMap);
		if(result > 0) {
			mv.setViewName("redirect:/project/my.do");
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/project/deleteProject.do", method=RequestMethod.GET)
	public ModelAndView deleteProject(ModelAndView mv
			, @RequestParam("projectNo") int projectNo) {
		List<SideProject> sproList = pService.getSideProjectByNo(projectNo);
		for (SideProject sideProject : sproList) {
			int spbProjectNo = sideProject.getSproNo();
			int result = pService.deleteSpboard(spbProjectNo);
			if(result < 0) {
				mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
				mv.addObject("url", "/project/myInfo.do");
				mv.setViewName("common/errorPage");
			}
		}
		int result2 = pService.deleteSideProject(projectNo);
		if(result2 < 0) {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		int result3 = pService.deleteProject(projectNo);
		if(result3 > 0) {
			mv.setViewName("redirect:/project/my.do");
		} else {
			mv.addObject("msg", "프로젝트 생성 실패.관리자에게 문의하세요.");
			mv.addObject("url", "/project/myInfo.do");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
