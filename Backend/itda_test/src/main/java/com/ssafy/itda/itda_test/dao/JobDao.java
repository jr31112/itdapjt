package com.ssafy.itda.itda_test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Job;

@Repository
public class JobDao {
	String ns = "ssafy.job.";

	@Autowired
	private SqlSession sqlSession;

	public Job getJob(Integer jid) {
		return sqlSession.selectOne(ns + "getJob", jid);
	}

	public void createJob(Job model) {
		sqlSession.insert(ns + "createJob", model);
	}

	public void deleteJob(Integer jid) {
		sqlSession.delete(ns + "deleteJob", jid);
	}

	public void updateJob(Job model) {
		sqlSession.update(ns + "updateJob", model);
	}

	public int createJobReturnJid(Job j) {
		sqlSession.insert(ns + "createJob", j);
		return sqlSession.selectOne(ns + "createJobReturnJid", j);
	}
}
