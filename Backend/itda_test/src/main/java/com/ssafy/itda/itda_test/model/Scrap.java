package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Scrap implements Serializable {

	private int uid;
	private String wid;
	public Scrap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Scrap(int uid, String wid) {
		super();
		this.uid = uid;
		this.wid = wid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	@Override
	public String toString() {
		return "Scrap [uid=" + uid + ", wid=" + wid + "]";
	}	
}
