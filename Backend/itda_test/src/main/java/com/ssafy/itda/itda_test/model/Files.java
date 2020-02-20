package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Files implements Serializable {
	private int fid;
	private int uid;
	private int stid;
	private String fname;
	private String url;
	private String createdAt;

	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Files [fid=" + fid + ", uid=" + uid + ", stid=" + stid + ", fname=" + fname + ", url=" + url
				+ ", createdAt=" + createdAt + "]";
	}

}
