package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.Job;

public interface IJobService {
	public Job getJob(Integer jid);
	public void createJob(Job job);
	public void deleteJob(Integer jid);
	public void updateJob(Job job);
}
