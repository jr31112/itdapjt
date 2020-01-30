package com.ssafy.itda.itda_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.JobDao;
import com.ssafy.itda.itda_test.model.Job;


@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	private JobDao jobDao;
	
	@Override
	public Job getJob(Integer jid) {
		return jobDao.getJob(jid);
	}

	@Override
	public void createJob(Job model) {
		jobDao.createJob(model);
	}
	
	@Override
	public void deleteJob(Integer jid) {
		jobDao.deleteJob(jid);
	}
	
	@Override
	public void updateJob(Job model) {
		jobDao.updateJob(model);
	}
}
