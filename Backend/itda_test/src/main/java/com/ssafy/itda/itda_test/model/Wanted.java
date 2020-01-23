package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Wanted implements Serializable {
	private int wid;
	private String wantedTitle;
	private int active;
	private String startDate;
	private String endDate;
	private String process;
	private String etc;
	private String question;
	private int vcnt;
	private int cid;

	public Wanted() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wanted(int wid, String wantedTitle, int active, String startDate, String endDate, String process, String etc,
			String question, int vcnt, int cid) {
		super();
		this.wid = wid;
		this.wantedTitle = wantedTitle;
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.process = process;
		this.etc = etc;
		this.question = question;
		this.vcnt = vcnt;
		this.cid = cid;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getWantedTitle() {
		return wantedTitle;
	}

	public void setWantedTitle(String wantedTitle) {
		this.wantedTitle = wantedTitle;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getVcnt() {
		return vcnt;
	}

	public void setVcnt(int vcnt) {
		this.vcnt = vcnt;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Wanted [wid=" + wid + ", wantedTitle=" + wantedTitle + ", active=" + active + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", process=" + process + ", etc=" + etc + ", question=" + question
				+ ", vcnt=" + vcnt + ", cid=" + cid + "]";
	}

}
