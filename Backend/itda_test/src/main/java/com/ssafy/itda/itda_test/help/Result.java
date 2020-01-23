package com.ssafy.itda.itda_test.help;

import java.io.Serializable;

public class Result implements Serializable {
	private String msg;
	private String state;
	private boolean scrap;

	@Override
	public String toString() {
		return "Result [msg=" + msg + ", state=" + state + ", scrap=" + scrap + "]";
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

	public boolean isScrap() {
		return scrap;
	}

	public void setScrap(boolean scrap) {
		this.scrap = scrap;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
}
