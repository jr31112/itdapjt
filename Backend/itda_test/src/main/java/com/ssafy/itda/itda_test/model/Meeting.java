package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Meeting implements Serializable {
	private int mid;
	private String title;
	private String startTime;
	private String endTime;
	private int stid;

	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	@Override
	public String toString() {
		return "Meeting [mid=" + mid + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", stid=" + stid + "]";
	}

}
