package com.ssafy.itda.itda_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.UserDao;
import com.ssafy.itda.itda_test.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void signUp(User u) {
		userDao.signUp(u);
	}

}
