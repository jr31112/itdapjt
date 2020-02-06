package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.StudyGroup;


public interface IStudyGroupService {
	List<StudyGroup> getStudyGroup(int stid);
	void createStudyGroup(StudyGroup model);
	void deleteStudyGroup(StudyGroup sg);
	StudyGroup getStudyGroupRel(StudyGroup model);
}
