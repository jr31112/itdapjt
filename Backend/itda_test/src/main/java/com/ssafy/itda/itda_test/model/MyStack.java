package com.ssafy.itda.itda_test.model;

import java.io.Serializable;
import java.util.List;

public class MyStack implements Serializable {
	private int uid;
	private int sid;
	private List<Integer> stacks;

	public MyStack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public List<Integer> getStacks() {
		return stacks;
	}

	public void setStacks(List<Integer> stacks) {
		this.stacks = stacks;
	}

	@Override
	public String toString() {
		return "MyStack [uid=" + uid + ", sid=" + sid + ", stacks=" + stacks + "]";
	}

}
