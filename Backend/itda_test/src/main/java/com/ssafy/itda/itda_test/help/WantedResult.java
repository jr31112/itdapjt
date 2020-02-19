package com.ssafy.itda.itda_test.help;

import java.io.Serializable;
import java.util.List;

import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Study;
import com.ssafy.itda.itda_test.model.Wanted;

public class WantedResult implements Serializable {
	// Company Info
	private Company company;

	// Wanted Info
	private Wanted wanted;

	// Stack Info
	private List<Stack> stacks;

	// Scrap Info
	private boolean isScrap;

	// Study Info
	private List<Study> studys;

	public WantedResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Wanted getWanted() {
		return wanted;
	}

	public void setWanted(Wanted wanted) {
		this.wanted = wanted;
	}

	public List<Stack> getStacks() {
		return stacks;
	}

	public void setStacks(List<Stack> stacks) {
		this.stacks = stacks;
	}

	public List<Study> getStudys() {
		return studys;
	}

	public void setStudys(List<Study> studys) {
		this.studys = studys;
	}

	public boolean isScrap() {
		return isScrap;
	}

	public void setScrap(boolean isScrap) {
		this.isScrap = isScrap;
	}

	@Override
	public String toString() {
		return "WantedResult [company=" + company + ", wanted=" + wanted + ", stacks=" + stacks + ", isScrap=" + isScrap
				+ ", studys=" + studys + "]";
	}

}
