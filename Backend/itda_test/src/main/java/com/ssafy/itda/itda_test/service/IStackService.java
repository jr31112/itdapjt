package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.WantedStack;
import com.ssafy.itda.itda_test.model.MyStack;
import com.ssafy.itda.itda_test.model.Stack;

public interface IStackService {
	void createStack(String s);
	void createMyStack(MyStack ms);
	void createWantedStack(WantedStack ws);
	List<Stack> getAllStacks();
	List<Stack> getWantedStacks(String wid);
	List<Stack> getMyStacks(int uid);
	void deleteMyStack(MyStack newms);
}
