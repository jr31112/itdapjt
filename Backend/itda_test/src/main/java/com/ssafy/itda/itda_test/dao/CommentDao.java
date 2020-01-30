package com.ssafy.itda.itda_test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Comment;

@Repository
public class CommentDao {
	String ns = "ssafy.comment.";
	
	@Autowired
	private SqlSession sqlSession;

	public void createComment(Comment model) {
		sqlSession.insert(ns + "createComment", model);
	}
	
	public Comment getComment(Integer cmid) {
		return sqlSession.selectOne(ns + "getComment", cmid);
	}
	
	public void deleteComment(Integer cmid) {
		sqlSession.delete(ns + "deleteComment", cmid);
	}
	
	public void updateComment(Comment model) {
		sqlSession.update(ns + "updateComment", model);
	}
}
