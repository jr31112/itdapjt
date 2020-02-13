package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Wanted implements Serializable {
	private String wid;
	private String wantedTitle;
	private int active;
	private String startDate;
	private String endDate;
	private int vcnt;
	private String cid;
	private String detail;
	private int jobType;

	public Wanted() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wanted(String wid, String wantedTitle, int active, String startDate, String endDate, int vcnt, String cid,
			String detail) {
		super();
		this.wid = wid;
		this.wantedTitle = wantedTitle;
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.vcnt = vcnt;
		this.cid = cid;
		this.detail = detail;
	}

	public Wanted(String wid, String wantedTitle, int active, String startDate, String endDate, int vcnt, String cid,
			String detail, int jobType) {
		super();
		this.wid = wid;
		this.wantedTitle = wantedTitle;
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.vcnt = vcnt;
		this.cid = cid;
		this.detail = detail;
		this.jobType = jobType;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
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

	public int getVcnt() {
		return vcnt;
	}

	public void setVcnt(int vcnt) {
		this.vcnt = vcnt;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getJobType() {
		return jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}

	@Override
	public String toString() {
		return "Wanted [wid=" + wid + ", wantedTitle=" + wantedTitle + ", active=" + active + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", vcnt=" + vcnt + ", cid=" + cid + ", detail=" + detail + "]";
	}

}
