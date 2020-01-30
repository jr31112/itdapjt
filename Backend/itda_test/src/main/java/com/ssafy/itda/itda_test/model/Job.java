package com.ssafy.itda.itda_test.model;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {
	private int jid;
	private String to;
	private String jname;
	private String jdetail;
	private String jtype;
	private String require;
	private String extra;
	private String place;
	private int wid;

	private List<Stack> stacks;

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(int jid, String to, String jname, String jdetail, String jtype, String require, String extra,
			String place, int wid) {
		super();
		this.jid = jid;
		this.to = to;
		this.jname = jname;
		this.jdetail = jdetail;
		this.jtype = jtype;
		this.require = require;
		this.extra = extra;
		this.place = place;
		this.wid = wid;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getJdetail() {
		return jdetail;
	}

	public void setJdetail(String jdetail) {
		this.jdetail = jdetail;
	}

	public String getJtype() {
		return jtype;
	}

	public void setJtype(String jtype) {
		this.jtype = jtype;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public List<Stack> getStacks() {
		return stacks;
	}

	public void setStacks(List<Stack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public String toString() {
		return "Job [jid=" + jid + ", to=" + to + ", jname=" + jname + ", jdetail=" + jdetail + ", jtype=" + jtype
				+ ", require=" + require + ", extra=" + extra + ", place=" + place + ", wid=" + wid + "]";
	}

}
