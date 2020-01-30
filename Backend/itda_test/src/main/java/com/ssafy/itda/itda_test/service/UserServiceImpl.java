package com.ssafy.itda.itda_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.UserDao;
import com.ssafy.itda.itda_test.model.Stack;
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
	
	@Override
	public User getUser(Integer uid) {
		return userDao.getUser(uid);
	}
	
	@Override
	public void deleteUser(Integer uid) {
		userDao.deleteUser(uid);
	}
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Override
	public void updateUser(User u) {
		userDao.updateUser(u);
	}

	@Override
	public List<Stack> getMyStacks(int uid) {
		return userDao.getMyStacks(uid);
	}

	@Override
	public List<Integer> getMyScrapWanteds(int uid) {
		return userDao.getMyScrapWanteds(uid);
	}
	
	@Override
	public void updatePermission(User model) {
		userDao.updatePermission(model);
	}
}
