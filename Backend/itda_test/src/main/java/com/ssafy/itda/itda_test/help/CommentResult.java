package com.ssafy.itda.itda_test.help;

import java.io.Serializable;

import com.ssafy.itda.itda_test.model.Comment;

public class CommentResult implements Serializable {
	private Comment comment;
	private String uname;
	private String uimg;
	
	public CommentResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentResult(Comment comment, String uname, String uimg) {
		super();
		this.comment = comment;
		this.uname = uname;
		this.uimg = uimg;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUimg() {
		return uimg;
	}

	public void setUimg(String uimg) {
		this.uimg = uimg;
	}

	@Override
	public String toString() {
		return "CommentResult [comment=" + comment + ", uname=" + uname + ", uimg=" + uimg + "]";
	}
	
}