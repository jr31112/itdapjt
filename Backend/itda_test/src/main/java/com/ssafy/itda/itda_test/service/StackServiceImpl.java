package com.ssafy.itda.itda_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.StackDao;
import com.ssafy.itda.itda_test.model.WantedStack;
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
	public void createWantedStack(WantedStack ws) {
		stackDao.createWantedStack(ws);
	}

	@Override
	public List<Stack> getAllStacks() {
		return stackDao.getAllStacks();
	}

	@Override
	public List<Stack> getWantedStacks(String wid) {
		return stackDao.getWantedStacks(wid);
	}

	@Override
	public List<Stack> getMyStacks(int uid) {
		return stackDao.getMyStacks(uid);
	}

	@Override
	public void deleteMyStack(MyStack newms) {
		stackDao.deleteMyStack(newms);
	}

	@Override
	public void requestStack(Stack model) {
		stackDao.requestStack(model);
	}

}
