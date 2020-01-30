package com.ssafy.itda.itda_test.help;

import java.io.Serializable;
import java.util.List;

import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Wanted;

public class WantedResult implements Serializable {
	// Company Info
	private Company company;

	// Wanted Info
	private Wanted wanted;

	// Jobs Info
	private List<Job> jobs;

	// Stack Info
	private List<Stack> stacks;

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

	@Override
	public String toString() {
		return "WantedResult [company=" + company + ", wanted=" + wanted + ", jobs=" + jobs + "]";
	}

	public Wanted getWanted() {
		return wanted;
	}

	public void setWanted(Wanted wanted) {
		this.wanted = wanted;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Stack> getStacks() {
		return stacks;
	}

	public void setStacks(List<Stack> stacks) {
		this.stacks = stacks;
	}

}
