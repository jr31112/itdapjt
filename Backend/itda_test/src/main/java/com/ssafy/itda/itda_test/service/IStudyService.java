package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.Study;

public interface IStudyService {
	List<Study> getAllStudy();
	Study getStudy(int stid);
	int createStudy(Study model);
	void deleteStudy(int stid);
	List<Study> getStudyByWanted(String wid);
	List<Study> getStudyByCompany(String cid);
}
