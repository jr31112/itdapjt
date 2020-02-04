package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.JobStack;
import com.ssafy.itda.itda_test.model.MyStack;
import com.ssafy.itda.itda_test.model.Stack;

public interface IStackService {
	void createStack(String s);
	void createMyStack(MyStack ms);
	void createJobStack(JobStack js);
	
	List<Stack> getAllStacks();
	List<Stack> getJobStacks(int jid);
	List<Stack> getMyStacks(int uid);
	void deleteMyStack(MyStack newms);
}
