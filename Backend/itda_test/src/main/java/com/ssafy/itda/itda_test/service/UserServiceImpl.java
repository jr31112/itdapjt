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
		// TODO Auto-generated method stub
		userDao.signUp(u);
	}
	
	@Override
	public User emailCheck(String email) {
		return userDao.emailCheck(email);
	}
	
	@Override
	public User login(User u) {
		return userDao.login(u);
	}
}
