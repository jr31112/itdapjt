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
	}
	
	public void deleteStudyGroup(int stid) {
		sqlSession.delete(ns + "deleteStudyGroup", stid);
	}
}
