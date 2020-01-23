package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Scrap implements Serializable {

	private int uid;
	private int wid;

	public Scrap() {
		super();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	@Override
	public String toString() {
		return "Scrap [uid=" + uid + ", wid=" + wid + "]";
	}

}
