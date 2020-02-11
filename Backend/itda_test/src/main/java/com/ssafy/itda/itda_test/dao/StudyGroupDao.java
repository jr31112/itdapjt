package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.StudyGroup;

@Repository
public class StudyGroupDao {
	String ns = "ssafy.studygroup.";

	@Autowired
	private SqlSession sqlSession;
	
	public List<StudyGroup> getStudyGroup(int stid) {
		return sqlSession.selectList(ns + "getStudyGroup", stid);
	}
	
	public void createStudyGroup(StudyGroup model) {
		sqlSession.insert(ns + "createStudyGroup", model);
		sqlSession.update(ns + "plusPcnt", model.getStid());
	}
	
	public void deleteStudyGroup(StudyGroup model) {
		sqlSession.delete(ns + "deleteStudyGroup", model);
		sqlSession.update(ns + "minusPcnt", model.getStid());
	}
	
	public StudyGroup getStudyGroupRel(StudyGroup model) {
		return sqlSession.selectOne(ns + "getStudyGroupRel", model);
	}

	public List<Integer> getMyStudyGroup(int uid) {
		return sqlSession.selectList(ns + "getMyStudyGroup", uid);
	}
}
