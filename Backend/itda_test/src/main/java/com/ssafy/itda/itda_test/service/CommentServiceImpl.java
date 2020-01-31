package com.ssafy.itda.itda_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.CommentDao;
import com.ssafy.itda.itda_test.model.Comment;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentDao commentDao;
	
	public void createComment(Comment model) {
		commentDao.createComment(model);
	}
	
	public Comment getComment(Integer cmid) {
		return commentDao.getComment(cmid);
	}

	public void deleteComment(Integer cmid) {
		commentDao.deleteComment(cmid);
	}
	
	public void updateComment(Comment model) {
		commentDao.updateComment(model);
	}
}
