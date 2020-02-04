package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.Job;

public interface IJobService {
	Job getJob(Integer jid);
	void createJob(Job job);
	void deleteJob(Integer jid);
	void updateJob(Job job);
}
