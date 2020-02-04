package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.Study;

public interface IStudyService {
	List<Study> getStudyList();
	Study getStudy(int stid);
	void createStudy(Study model);
	void deleteStudy(int stid);
}
