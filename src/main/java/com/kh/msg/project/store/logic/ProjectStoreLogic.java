package com.kh.msg.project.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.msg.project.domain.Project;
import com.kh.msg.project.domain.SideProject;
import com.kh.msg.project.domain.SideProjectBoard;
import com.kh.msg.project.store.ProjectStore;

@Repository
public class ProjectStoreLogic implements ProjectStore {

	@Override
	public int addProject(SqlSession session, Map<String, String> paramMap) {
		int result = session.insert("ProjectMapper.addProject", paramMap);
		return result;
	}

	@Override
	public List<Project> getProjectById(SqlSession session, String userId) {
		List<Project> pList = session.selectList("ProjectMapper.getProjectById", userId);
		return pList;
	}

	@Override
	public Project getProjectByNo(SqlSession session, Project sendProject) {
		Project pOne = session.selectOne("ProjectMapper.getProjectByNo", sendProject);
		return pOne;
	}

	@Override
	public int addSideProject(SqlSession session, Map<String, Object> paramMap) {
		int result = session.insert("SideProjectMapper.addSideProject", paramMap);
		return result;
	}

	@Override
	public List<SideProject> getSideProjectByNo(SqlSession session, int sproProjectNo) {
		List<SideProject> spList = session.selectList("SideProjectMapper.getSideProjectByNo", sproProjectNo);
		return spList;
	}

	@Override
	public SideProject getSideProjectBySproNo(SqlSession session, int sproNo) {
		SideProject spOne = session.selectOne("SideProjectMapper.getSideProjectBySproNo", sproNo);
		return spOne;
	}

	@Override
	public List<SideProjectBoard> getSideProjectBoardList(SqlSession session, int sproNo) {
		List<SideProjectBoard> spbList = session.selectList("SideProjectBoardMapper.getSideProjectBoardList", sproNo);
		return spbList;
	}

	@Override
	public int addBoard(SqlSession session, SideProjectBoard spBoard) {
		int result = session.insert("SideProjectBoardMapper.addBoard", spBoard);
		return result;
	}

	@Override
	public int inviteUser(SqlSession session, Project project) {
		int result = session.insert("ProjectMapper.inviteUser", project);
		return result;
	}

	@Override
	public int modifyProject(SqlSession session, Map<String, Object> paramMap) {
		int result = session.update("ProjectMapper.modifyProject", paramMap);
		return result;
	}

	@Override
	public int modifySideProject(SqlSession session, Map<String, Object> paramMap) {
		int result = session.update("SideProjectMapper.modifySideProject", paramMap);
		return result;
	}

	@Override
	public int leaveProject(SqlSession session, Map<String, Object> paramMap) {
		int result = session.delete("ProjectMapper.leaveProject", paramMap);
		return result;
	}

	@Override
	public int deleteSpboard(SqlSession session, int spbProjectNo) {
		int result = session.delete("SideProjectBoardMapper.deleteSpboard", spbProjectNo);
		return result;
	}

	@Override
	public int deleteSideProject(SqlSession session, int projectNo) {
		int result2 = session.delete("SideProjectMapper.deleteSideProject", projectNo);
		return result2;
	}

	@Override
	public int deleteProject(SqlSession session, int projectNo) {
		int result3 = session.delete("ProjectMapper.deleteProject", projectNo);
		return result3;
	}


}
