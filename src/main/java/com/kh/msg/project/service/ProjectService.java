package com.kh.msg.project.service;

import java.util.List;
import java.util.Map;

import com.kh.msg.project.domain.Project;
import com.kh.msg.project.domain.SideProject;
import com.kh.msg.project.domain.SideProjectBoard;

public interface ProjectService {

	/**
	 * 프로젝트 만들기
	 * @param paramMap
	 * @return
	 */
	int addProject(Map<String, String> paramMap);

	/**
	 * 유저가 포함된 프로젝트 찾기
	 * @param userId
	 * @return
	 */
	List<Project> getProjectById(String userId);

	/**
	 * 번호로 프로젝트 찾기
	 * @param sproProjectNo
	 * @param userId 
	 * @return
	 */
	Project getProjectByNo(Project sendProject);

	/**
	 * 사이드 프로젝트 만들기
	 * @param paramMap
	 * @return
	 */
	int addSideProject(Map<String, Object> paramMap);

	/**
	 * 프로젝트에 포함된 사이드 프로젝트 찾기
	 * @param projectNo
	 * @param userId 
	 * @return
	 */
	List<SideProject> getSideProjectByNo(int projectNo);

	/**
	 * 사이드 프로젝트 번호로 사이드 프로젝트 찾기
	 * @param sproNo
	 * @return
	 */
	SideProject getSideProjectBySproNo(int sproNo);

	/**
	 * 사이드 프로젝트 번호로 사이드 프로젝트 보드 리스트 찾기
	 * @param sproNo
	 * @return
	 */
	List<SideProjectBoard> getSideProjectBoardList(int sproNo);

	/**
	 * 사이드 프로젝트 보드 추가
	 * @param spBoard
	 * @return
	 */
	int addBoard(SideProjectBoard spBoard);

	/**
	 * 유저 추가
	 * @param paramMap
	 * @return
	 */
	int inviteUser(Project project);

	/**
	 * 프로젝트 이름 수정
	 * @param paramMap
	 * @return
	 */
	int modifyProject(Map<String, Object> paramMap);

	/**
	 * 사이드 프로젝트 수정
	 * @param paramMap
	 * @return
	 */
	int modifySideProject(Map<String, Object> paramMap);

	/**
	 * 프로젝트 나가기
	 * @param paramMap
	 * @return
	 */
	int leaveProject(Map<String, Object> paramMap);

	/**
	 * 사이드 프로젝트 보드 삭제
	 * @param spbProjectNo
	 * @return
	 */
	int deleteSpboard(int spbProjectNo);

	/**
	 * 사이드 프로젝트 삭제
	 * @param projectNo
	 * @return
	 */
	int deleteSideProject(int projectNo);

	/**
	 * 프로젝트 삭제
	 */
	int deleteProject(int projectNo);

}
