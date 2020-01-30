package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.Comment;

public interface ICommentService {
	public void createComment(Comment model);
	public Comment getComment(Integer cmid);
	public void deleteComment(Integer cmid);
	public void updateComment(Comment model);
}
