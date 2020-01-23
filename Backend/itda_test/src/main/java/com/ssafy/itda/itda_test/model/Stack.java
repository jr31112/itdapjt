package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Stack implements Serializable {
	private int sid;
	private String tname;
	private int cnt;

	public Stack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Stack [sid=" + sid + ", tname=" + tname + ", cnt=" + cnt + "]";
	}

}
