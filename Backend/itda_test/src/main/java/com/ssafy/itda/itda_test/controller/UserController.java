package com.ssafy.itda.itda_test.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
		logger.info("1-1-------------signUp-----------------------------" + new Date());
		logger.info("1-1-------------signUp-----------------------------" + model);
		User user = userService.emailCheck(model.getEmail());
		UserResult ur = new UserResult();
		if (user != null) {
			ur.setMsg("중복된 아이디입니다.");
			ur.setState("fail");
		} else {
			userService.signUp(model);
			ur.setMsg("성공적으로 회원가입이 완료되었습니다.");
			ur.setState("success");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = " email 중복을 확인한다.", response = UserResult.class)
	@RequestMapping(value = "/emailCheck/{email}", method = RequestMethod.GET)
	public ResponseEntity<UserResult> signUp(@PathVariable String email) throws Exception {
		logger.info("1-2-------------emailCheck-------------------------" + new Date());
		logger.info("1-2-------------emailCheck-------------------------" + email);

		User user = userService.emailCheck(email);
		UserResult ur = new UserResult();
		if (user != null) {
			ur.setMsg("중복된 아이디입니다.");
			ur.setState("fail");
		} else {
			ur.setMsg("사용가능한 아이디입니다.");
			ur.setState("success");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}
	
	@ApiOperation(value = " 로그인을 시도한다.", response = UserResult.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserResult> login(@RequestBody User model) throws Exception {
		logger.info("1-3-------------login------------------------------" + new Date());
		logger.info("1-3-------------login------------------------------" + model);

		User user = userService.login(model);
		UserResult ur = new UserResult();
		if (user != null) {
			ur.setUid(user.getUid());
			ur.setEmail(user.getEmail());
			ur.setAuth(user.getAuth());
			ur.setMsg("성공적으로 로그인 되었습니다.");
			ur.setState("success");
		} else {
			ur.setMsg("이메일 또는 비밀번호가 일치하지 않습니다.");
			ur.setState("fail");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}
}