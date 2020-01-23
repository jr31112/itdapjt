package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.User;

public interface IUserService {
	void signUp(User u);
	User emailCheck(String email);
	User login(User u);
}
