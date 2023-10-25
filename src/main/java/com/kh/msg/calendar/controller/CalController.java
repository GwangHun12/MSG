package com.kh.msg.calendar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.msg.calendar.domain.CalVO;
import com.kh.msg.calendar.service.GroupService;
import com.kh.msg.calendar.service.UserService;
import com.kh.msg.calendar.service.calService;
@Controller
@RequestMapping("/schedule")
public class CalController {
	//일정 컨트롤러

	@Autowired
	private calService calservice;
	@Autowired
	private GroupService groupservice;
	@Autowired
	private UserService userservice;
	// 일정 첫page로 연결한다.

	@GetMapping("/calendar.do")
	public ModelAndView schedList_(ModelAndView mv) {
		mv.setViewName("calendar/calendar");
		return mv;
	}
	@RequestMapping("g_pic")
	@ResponseBody
	public HashMap<String, Object> g_pic(int g_code, HttpSession session){
		HashMap<String, Object> map = new HashMap<>();
		String id = (String) session.getAttribute("id");
		if(g_code == 0)	{
			map.put("upic", groupservice.upic(id));
			System.out.println(map);
			return map;
		}else{
			map.put("gpic", groupservice.gread(g_code));
			System.out.println(map);
			return map;
		}
		
	}
	
	// 달력에 내가 원하는 정보 값을 보내준다.
	@GetMapping("/getScheduleList.do")
	@ResponseBody
	public List<CalVO> group_scheduleList(@RequestParam(value = "g_code", required = false) Integer g_code, HttpSession session) {
		String id = (String)session.getAttribute("userId");
		List<CalVO> listvo;
		if(g_code == 0){
			listvo = calservice.myscheduleList(id);
			for (CalVO vo : listvo) {
				if (vo.getAllDay().equals("1")) {
					vo.setAllDayjs(true);
				} else if (vo.getAllDay().equals("0")) {
					vo.setAllDayjs(false);
				}
			}
		}else{
			listvo = calservice.scheduleList(g_code);
			for (CalVO vo : listvo) {
				if (vo.getAllDay().equals("1")) {
					vo.setAllDayjs(true);
				} else if (vo.getAllDay().equals("0")) {
					vo.setAllDayjs(false);
				}
			}
		}				
//		System.out.println("list"+listvo);
		return listvo;
	}
	
	// 일정을 저장한다 .
	@GetMapping("/scheduleinsert.do")
	@ResponseBody
	public int scheduleinsert(@RequestParam("start") String start,@RequestParam("projectNo") int projectNo,
            @RequestParam("end") String end,CalVO vo, HttpSession session) throws ParseException  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parsedDate = sdf.parse(start);
		Date parsedEndDate = sdf.parse(end);
		vo.setStart(parsedDate);
		vo.setEnd(parsedEndDate);
		vo.setProjectNo(projectNo);
		String id = (String) session.getAttribute("userId");
		vo.setUserName(id);
		String color = vo.getBackgroundColor();
		String[] array = color.split("#");
		color = array[1];
		vo.setBackgroundColor(color);
//		System.out.println(vo.toString());
		int result =calservice.scheduleinsert(vo);
		return result;
	}

	// 전체 업데이트
	@GetMapping("/scheduleupdate.do")
	@ResponseBody
	public int scheduleupdate(CalVO vo,@RequestParam("start") String start,@RequestParam("calno")int calno, @RequestParam("end") String end) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parsedDate = sdf.parse(start);
		Date parsedEndDate = sdf.parse(end);
//		System.out.println("컨트롤러");
		vo.setStart(parsedDate);
		vo.setEnd(parsedEndDate);
		vo.setCalno(calno);
		String color = vo.getBackgroundColor();
		String[] array = color.split("#");
		color = array[1];
		vo.setBackgroundColor(color);
//		System.out.println(vo);
		int result = calservice.scheduleupdate(vo);
		return result;
	}

	// 시간, 날짜 업데이트
	@GetMapping("/dragupdate.do")
	@ResponseBody
	public int dragupdate(CalVO vo 
            ) {
		    System.out.println(vo);
		    int result = calservice.dragupdate(vo);
		    return result;
	}

	// 일정 삭제
	@GetMapping("/scheduledelete.do")
	@ResponseBody
	public int scheduledelete(int calno) {
		
		int result= calservice.scheduledelete(calno);
		return result;
	}
	
	
}
