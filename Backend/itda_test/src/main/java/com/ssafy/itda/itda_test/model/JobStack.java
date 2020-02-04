package com.ssafy.itda.itda_test.model;

import java.io.Serializable;
import java.util.List;

public class JobStack implements Serializable {
	private int jid;
	private int sid;
	private List<Integer> stacks;

	public JobStack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
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
		return "JobStack [jid=" + jid + ", sid=" + sid + ", stacks=" + stacks + "]";
	}

}
