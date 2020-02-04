package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Study implements Serializable {
	private int stid;
	private String stname;
	private int maxPcnt;
	private int stype;
	private int typeFk;
	private String typeName;
	private int sgroup;
	private String content;
	private int captain;

	public Study() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Study(int stid, String stname, int maxPcnt, int stype, int typeFk, String typeName, int sgroup,
			String content, int captain) {
		super();
		this.stid = stid;
		this.stname = stname;
		this.maxPcnt = maxPcnt;
		this.stype = stype;
		this.typeFk = typeFk;
		this.typeName = typeName;
		this.sgroup = sgroup;
		this.content = content;
		this.captain = captain;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public int getMaxPcnt() {
		return maxPcnt;
	}

	public void setMaxPcnt(int maxPcnt) {
		this.maxPcnt = maxPcnt;
	}

	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
	}

	public int getTypeFk() {
		return typeFk;
	}

	public void setTypeFk(int typeFk) {
		this.typeFk = typeFk;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSgroup() {
		return sgroup;
	}

	public void setSgroup(int sgroup) {
		this.sgroup = sgroup;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCaptain() {
		return captain;
	}

	public void setCaptain(int captain) {
		this.captain = captain;
	}

	@Override
	public String toString() {
		return "Study [stid=" + stid + ", stname=" + stname + ", maxPcnt=" + maxPcnt + ", stype=" + stype + ", typeFk="
				+ typeFk + ", typeName=" + typeName + ", sgroup=" + sgroup + ", content=" + content + ", captain="
				+ captain + "]";
	}

}
