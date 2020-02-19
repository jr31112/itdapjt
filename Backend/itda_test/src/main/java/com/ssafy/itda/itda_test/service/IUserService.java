package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.User;

public interface IUserService {
	void signUp(User u);
	User emailCheck(String email);
	User login(User u);
	User getUser(Integer uid);
	void deleteUser(Integer uid);
	List<User> getAllUsers();
	void updateUser(User u);
	List<Stack> getMyStacks(int uid);
	List<String> getMyScrapWanteds(int uid);
	void updatePermission(User model);
	void imgUpload(User user);
	void updateUserWithoutPw(User u);
}
