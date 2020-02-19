package com.ssafy.itda.itda_test.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
	private int cmid;
	private int uid;
	private int stid;
	private String content;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int cmid, int uid, int stid, String content, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.cmid = cmid;
		this.uid = uid;
		this.stid = stid;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Comment [cmid=" + cmid + ", uid=" + uid + ", stid=" + stid + ", content=" + content + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
