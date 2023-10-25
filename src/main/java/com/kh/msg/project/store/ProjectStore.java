package com.kh.msg.project.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.msg.project.domain.Project;
import com.kh.msg.project.domain.SideProject;
import com.kh.msg.project.domain.SideProjectBoard;

public interface ProjectStore {

	/**
	 * 프로젝트 만들기
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int addProject(SqlSession session, Map<String, String> paramMap);

	/**
	 * 유저가 포함된 프로젝트 찾기
	 * @param session
	 * @param userId
	 * @return
	 */
	List<Project> getProjectById(SqlSession session, String userId);

	/**
	 * 번호로 프로젝트 찾기
	 * @param session
	 * @param sproProjectNo
	 * @param userId 
	 * @return
	 */
	Project getProjectByNo(SqlSession session, Project sendProject);

	/**
	 * 사이드 프로젝트 만들기
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int addSideProject(SqlSession session, Map<String, Object> paramMap);

	/**
	 * 프로젝트에 포함된 사이드 프로젝트 찾기
	 * @param session
	 * @param projectNo
	 * @return
	 */
	List<SideProject> getSideProjectByNo(SqlSession session, int sproProjectNo);

	/**
	 * 사이드 프로젝트 번호로 사이드 프로젝트 찾기
	 * @param session
	 * @param sproNo
	 * @return
	 */
	SideProject getSideProjectBySproNo(SqlSession session, int sproNo);

	/**
	 * 사이드 프로젝트 번호로 사이드 프로젝트 보드 리스트 찾기
	 * @param session
	 * @param sproNo
	 * @return
	 */
	List<SideProjectBoard> getSideProjectBoardList(SqlSession session, int sproNo);

	/**
	 * 사이드 프로젝트 보드 추가
	 * @param session
	 * @param spBoard
	 * @return
	 */
	int addBoard(SqlSession session, SideProjectBoard spBoard);

	/**
	 * 유저 추가
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int inviteUser(SqlSession session, Project project);

	/**
	 * 프로젝트 이름 수정
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int modifyProject(SqlSession session, Map<String, Object> paramMap);

	/**
	 * 사이드 프로젝트 수정
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int modifySideProject(SqlSession session, Map<String, Object> paramMap);

	/**
	 * 프로젝트 나가기
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int leaveProject(SqlSession session, Map<String, Object> paramMap);

	/**
	 * 사이드 프로젝트 보드 삭제
	 * @param session
	 * @param spbProjectNo
	 * @return
	 */
	int deleteSpboard(SqlSession session, int spbProjectNo);

	/**
	 * 사이드 프로젝트 삭제
	 * @param session
	 * @param projectNo
	 * @return
	 */
	int deleteSideProject(SqlSession session, int projectNo);

	/**
	 * 프로젝트 삭제
	 * @param session
	 * @param projectNo
	 * @return
	 */
	int deleteProject(SqlSession session, int projectNo);

}
