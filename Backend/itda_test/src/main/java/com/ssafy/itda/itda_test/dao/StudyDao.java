package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Study;

@Repository
public class StudyDao {

	String ns = "ssafy.study.";

	@Autowired
	private SqlSession sqlSession;
	
	public List<Study> getAllStudy() {
		return sqlSession.selectList(ns + "getAllStudy");
	}
	
	public Study getStudy(int stid) {
		return sqlSession.selectOne(ns + "getStudy", stid);
		
	}
	
	public int createStudy(Study model) {
		sqlSession.insert(ns + "createStudy", model);
		return sqlSession.selectOne(ns + "getStid", model);
	}
	
	public void deleteStudy(int stid) {
		sqlSession.delete(ns + "deleteStudy", stid);
	}
}
