package com.ssafy.itda.itda_test.help;

import java.io.Serializable;

import com.ssafy.itda.itda_test.model.Study;

public class StudyResult implements Serializable {
	private Study study;

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

	@Override
	public String toString() {
		return "StudyResult [study=" + study + ", msg=" + msg + ", state=" + state + "]";
	}

}
