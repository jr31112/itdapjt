package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.User;

@Repository
public class UserDao {
	String ns = "ssafy.user.";

	@Autowired
	private SqlSession sqlSession;

	public void signUp(User u) {
		sqlSession.insert(ns + "signUp", u);
	}

	public User emailCheck(String email) {
		return sqlSession.selectOne(ns + "emailCheck", email);
	}

	public User login(User u) {
		return sqlSession.selectOne(ns + "login", u);
	}

	public User getUser(Integer uid) {
		return sqlSession.selectOne(ns + "getUser", uid);
	}

	public void deleteUser(Integer uid) {
		sqlSession.delete(ns + "deleteUser", uid);
	}

	public List<User> getAllUsers() {
		return sqlSession.selectList(ns + "getAllUsers");
	}

	public void updateUser(User u) {
		sqlSession.update(ns + "updateUser", u);
	}

	public List<Stack> getMyStacks(int uid) {
		return sqlSession.selectList(ns + "getMyStacks", uid);
	}
	
	public List<String> getMyScrapWanteds(int uid){
		return sqlSession.selectList(ns + "getMyScrapWanteds", uid);
	}
	
	public void updatePermission(User model) {
		sqlSession.update(ns + "updatePermission", model);
	}

	public void imgUpload(User user) {
		sqlSession.update(ns + "imgUpload", user);
	}
}
