package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.JobStack;
import com.ssafy.itda.itda_test.model.MyStack;
import com.ssafy.itda.itda_test.model.Stack;

@Repository
public class StackDao {

	String ns = "ssafy.stack.";

	@Autowired
	private SqlSession sqlSession;

	public void createStack(String s) {
		sqlSession.insert(ns + "createStack", s);
	}

	public void createMyStack(MyStack ms) {
		sqlSession.insert(ns + "createMyStack", ms);
	}

	public void createJobStack(JobStack js) {
		sqlSession.insert(ns + "createJobStack", js);
		sqlSession.update(ns + "updateStackCnt", js.getSid());
	}

	public List<Stack> getAllStacks() {
		return sqlSession.selectList(ns + "getAllStacks");
	}

	public List<Stack> getJobStacks(int jid) {
		return sqlSession.selectList(ns + "getJobStacks", jid);
	}

	public List<Stack> getMyStacks(int uid) {
		return sqlSession.selectList(ns + "getMyStacks", uid);
	}

	public void deleteMyStack(MyStack newms) {
		sqlSession.delete(ns + "deleteMyStack", newms);
	}

}
