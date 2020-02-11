package com.ssafy.itda.itda_test.model;

import java.io.Serializable;
import java.util.List;

public class WantedStack implements Serializable {
	private String wid;
	private int sid;
	private List<Integer> stacks;

	public WantedStack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WantedStack(String wid, int sid, List<Integer> stacks) {
		super();
		this.wid = wid;
		this.sid = sid;
		this.stacks = stacks;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
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
		return "WantedStack [wid=" + wid + ", sid=" + sid + ", stacks=" + stacks + "]";
	}
	
}
