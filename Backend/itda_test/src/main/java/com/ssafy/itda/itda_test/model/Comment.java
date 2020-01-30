package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Comment implements Serializable {
	private int cmid;
	private int uid;
	private int wid;
	private String writer;
	private String content;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int cmid, int uid, int wid, String writer, String content) {
		super();
		this.cmid = cmid;
		this.uid = uid;
		this.wid = wid;
		this.writer = writer;
		this.content = content;
	}

	public int getCmid() {
		return cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [cmid=" + cmid + ", uid=" + uid + ", wid=" + wid + ", writer=" + writer + ", content=" + content
				+ "]";
	}

}
