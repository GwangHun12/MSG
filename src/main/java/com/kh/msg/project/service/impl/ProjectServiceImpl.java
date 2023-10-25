package com.kh.msg.project.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.project.domain.Project;
import com.kh.msg.project.domain.SideProject;
import com.kh.msg.project.domain.SideProjectBoard;
import com.kh.msg.project.service.ProjectService;
import com.kh.msg.project.store.ProjectStore;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private ProjectStore pStore;

	@Override
	public int addProject(Map<String, String> paramMap) {
		int result = pStore.addProject(session, paramMap);
		return result;
	}

	@Override
	public List<Project> getProjectById(String userId) {
		List<Project> pList = pStore.getProjectById(session, userId);
		return pList;
	}

	@Override
	public Project getProjectByNo(Project sendProject) {
		Project pOne = pStore.getProjectByNo(session, sendProject);
		return pOne;
	}

	@Override
	public int addSideProject(Map<String, Object> paramMap) {
		int result = pStore.addSideProject(session, paramMap);
		return result;
	}

	@Override
	public List<SideProject> getSideProjectByNo(int sproProjectNo) {
		List<SideProject> spList = pStore.getSideProjectByNo(session, sproProjectNo);
		return spList;
	}

	@Override
	public SideProject getSideProjectBySproNo(int sproNo) {
		SideProject spOne = pStore.getSideProjectBySproNo(session, sproNo);
		return spOne;
	}

	@Override
	public List<SideProjectBoard> getSideProjectBoardList(int sproNo) {
		List<SideProjectBoard> spbList = pStore.getSideProjectBoardList(session, sproNo);
		return spbList;
	}

	@Override
	public int addBoard(SideProjectBoard spBoard) {
		int result = pStore.addBoard(session, spBoard);
		return result;
	}

	@Override
	public int inviteUser(Project project) {
		int result = pStore.inviteUser(session, project);
		return result;
	}

	@Override
	public int modifyProject(Map<String, Object> paramMap) {
		int result = pStore.modifyProject(session, paramMap);
		return result;
	}

	@Override
	public int modifySideProject(Map<String, Object> paramMap) {
		int result = pStore.modifySideProject(session, paramMap);
		return result;
	}

	@Override
	public int leaveProject(Map<String, Object> paramMap) {
		int result = pStore.leaveProject(session, paramMap);
		return result;
	}

	@Override
	public int deleteSpboard(int spbProjectNo) {
		int result = pStore.deleteSpboard(session, spbProjectNo);
		return result;
	}

	@Override
	public int deleteSideProject(int projectNo) {
		int result2 = pStore.deleteSideProject(session, projectNo);
		return result2;
	}

	@Override
	public int deleteProject(int projectNo) {
		int result3 = pStore.deleteProject(session, projectNo);
		return result3;
	}
}
