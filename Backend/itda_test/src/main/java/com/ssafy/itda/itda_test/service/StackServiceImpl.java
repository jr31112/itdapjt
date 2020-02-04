package com.ssafy.itda.itda_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.StackDao;
import com.ssafy.itda.itda_test.model.JobStack;
import com.ssafy.itda.itda_test.model.MyStack;
import com.ssafy.itda.itda_test.model.Stack;

@Service
public class StackServiceImpl implements IStackService {

	@Autowired
	private StackDao stackDao;

	@Override
	public void createStack(String s) {
		stackDao.createStack(s);
	}

	@Override
	public void createMyStack(MyStack ms) {
		stackDao.createMyStack(ms);
	}

	@Override
	public void createJobStack(JobStack js) {
		stackDao.createJobStack(js);
	}

	@Override
	public List<Stack> getAllStacks() {
		return stackDao.getAllStacks();
	}

	@Override
	public List<Stack> getJobStacks(int jid) {
		return stackDao.getJobStacks(jid);
	}

	@Override
	public List<Stack> getMyStacks(int uid) {
		return stackDao.getMyStacks(uid);
	}

	@Override
	public void deleteMyStack(MyStack newms) {
		stackDao.deleteMyStack(newms);
	}

}
