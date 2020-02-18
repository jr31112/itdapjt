package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.WantedStack;
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

	public void createWantedStack(WantedStack ws) {
		sqlSession.insert(ns + "createWantedStack", ws);
		sqlSession.update(ns + "updateStackCnt", ws.getSid());
	}

	public List<Stack> getAllStacks() {
		return sqlSession.selectList(ns + "getAllStacks");
	}

	public List<Stack> getWantedStacks(String wid) {
		return sqlSession.selectList(ns + "getWantedStacks", wid);
	}

	public List<Stack> getMyStacks(int uid) {
		return sqlSession.selectList(ns + "getMyStacks", uid);
	}

	public void deleteMyStack(MyStack newms) {
		sqlSession.delete(ns + "deleteMyStack", newms);
	}

	public void requestStack(Stack model) {
		sqlSession.insert(ns + "requestStack", model);
	}

}
