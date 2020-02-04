package com.ssafy.itda.itda_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.StudyGroupDao;
import com.ssafy.itda.itda_test.model.StudyGroup;

@Service
public class StudyGroupServiceImpl implements IStudyGroupService {

	@Autowired
	private StudyGroupDao studyGroupDao;
	
	@Override
	public List<StudyGroup> getStudyGroup(int stid) {
		// TODO Auto-generated method stub
		return studyGroupDao.getStudyGroup(stid);
	}

	@Override
	public void createStudyGroup(StudyGroup model) {
		// TODO Auto-generated method stub
		studyGroupDao.createStudyGroup(model);
	}

	@Override
	public void deleteStudyGroup(StudyGroup model) {
		// TODO Auto-generated method stub
		studyGroupDao.deleteStudyGroup(model);
	}

}
