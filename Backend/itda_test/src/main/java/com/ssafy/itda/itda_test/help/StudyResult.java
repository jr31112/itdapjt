package com.ssafy.itda.itda_test.help;

import java.io.Serializable;
import java.util.List;

import com.ssafy.itda.itda_test.model.Comment;
import com.ssafy.itda.itda_test.model.Study;

public class StudyResult implements Serializable {
	private Study study;
	private List<Comment> comments;

	private String msg;
	private String state;

	public StudyResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyResult(Study study, String msg, String state) {
		super();
		this.study = study;
		this.msg = msg;
		this.state = state;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "StudyResult [study=" + study + ", comments=" + comments + ", msg=" + msg + ", state=" + state + "]";
	}

}
