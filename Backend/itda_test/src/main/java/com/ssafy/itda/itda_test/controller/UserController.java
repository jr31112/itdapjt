package com.ssafy.itda.itda_test.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.itda.itda_test.help.UserResult;
import com.ssafy.itda.itda_test.model.User;
import com.ssafy.itda.itda_test.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@ApiOperation(value = " 새로운 회원의 정보를 입력한다.", response = UserResult.class)
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<UserResult> signUp(@RequestBody User model) throws Exception {
		logger.info("5-------------signUp-----------------------------" + new Date());
		logger.info("5-------------signUp-----------------------------" + model);
		
		userService.signUp(model);
		UserResult ur = new UserResult();
		ur.setMsg("성공적으로 회원가입이 완료되었습니다.");
		ur.setState("success");
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

}