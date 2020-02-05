package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.Comment;

public interface ICommentService {
	void createComment(Comment model);
	Comment getComment(Integer cmid);
	void deleteComment(Integer cmid);
	void updateComment(Comment model);
}
