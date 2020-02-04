package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class StudyGroup implements Serializable {
	private int sgid;
	private int uid;
	private int stid;

	public StudyGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyGroup(int sgid, int uid, int stid) {
		super();
		this.sgid = sgid;
		this.uid = uid;
		this.stid = stid;
	}

	public int getSgid() {
		return sgid;
	}

	public void setSgid(int sgid) {
		this.sgid = sgid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	@Override
	public String toString() {
		return "StudyGroup [sgid=" + sgid + ", uid=" + uid + ", stid=" + stid + "]";
	}

}
