package com.ssafy.itda.itda_test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ssafy.itda.itda_test.service.JwtServiceImpl;

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

	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = "새로운 회원의 정보를 입력한다.", response = UserResult.class)
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<UserResult> signUp(@RequestBody User model) throws Exception {
		logger.info("1-1-------------signUp-----------------------------" + new Date());
		logger.info("1-1-------------signUp-----------------------------" + model);
		User user = userService.emailCheck(model.getEmail());
		UserResult ur = new UserResult();
		if (model.getEmail() == null || model.getPw() == null || model.getUname() == null) {
			ur.setMsg("입력되지 않은 필수값이 존재합니다.");
			ur.setState("fail");
			return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
		}
		if (user != null) {
			ur.setMsg("이미 존재하는 회원ID 입니다.");
			ur.setState("fail");
			return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
		}
		userService.signUp(model);
		ur.setMsg("성공적으로 회원가입이 완료되었습니다.");
		ur.setState("success");
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = "email 중복을 확인한다.", response = UserResult.class)
	@RequestMapping(value = "/emailCheck/{email}", method = RequestMethod.GET)
	public ResponseEntity<UserResult> emailCheck(@PathVariable String email) throws Exception {
		logger.info("1-2-------------emailCheck-------------------------" + new Date());
		logger.info("1-2-------------emailCheck-------------------------" + email);

		User user = userService.emailCheck(email);
		UserResult ur = new UserResult();
		if (user == null || user.getEmail() == null || user.getEmail().equals("")) {
			ur.setMsg("사용가능한 아이디입니다.");
			ur.setState("success");
		} else {
			ur.setMsg("중복된 아이디입니다.");
			ur.setState("fail");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = "로그인을 시도한다.", response = UserResult.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserResult> login(@RequestBody User model, HttpServletResponse response) throws Exception {
		logger.info("1-3-------------login------------------------------" + new Date());
		logger.info("1-3-------------login------------------------------" + model);
		System.out.println(model);
		System.out.println(response);
		User user = userService.login(model);
		UserResult ur = new UserResult();
		if (user == null || user.getEmail() == null || user.getEmail().equals("")) {
			ur.setMsg("이메일 또는 비밀번호가 일치하지 않습니다.");
			ur.setState("fail");
		} else {
			String token = jwtService.create(user);
			response.setHeader("jwt-auth-token", token);
			ur.setUid(user.getUid());
			ur.setUname(user.getUname());
			ur.setEmail(user.getEmail());
			ur.setAuth(user.getAuth());
			ur.setMsg("성공적으로 로그인 되었습니다.");
			ur.setState("success");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = "토큰 정보를 확인한다.", response = Map.class)
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getInfo(@RequestBody User model, HttpServletRequest req)
			throws Exception {
		logger.info("1-3-------------getInfo------------------------------" + new Date());
		logger.info("1-3-------------getInfo------------------------------" + model);
		System.out.println(model);
		System.out.println(req);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			resultMap.put("status", true);
			resultMap.put("state", "success");
			resultMap.put("request_body", model);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패", e);
			resultMap.put("msg", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "ID에 해당하는 회원의 정보를 반환한다.", response = UserResult.class)
	@RequestMapping(value = "/getUser/{uid}", method = RequestMethod.POST)
	public ResponseEntity<UserResult> getUser(@RequestBody int uid) throws Exception {
		logger.info("1-4-------------getUser------------------------------" + new Date());
		logger.info("1-4-------------getUser------------------------------" + uid);

		User user = userService.getUser(uid);
		UserResult ur = new UserResult();
		if (user == null || user.getEmail() == null || user.getEmail().equals("")) {
			ur.setMsg("회원정보를 가져오는데 실패했습니다.");
			ur.setState("fail");
		} else {
			ur.setMsg("회원정보를 성공적으로 가져왔습니다.");
			ur.setUid(user.getUid());
			ur.setUname(user.getUname());
			ur.setEmail(user.getEmail());
			ur.setAuth(user.getAuth());
			ur.setMajor(user.getMajor());
			ur.setUimg(user.getUimg());
			ur.setState("success");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = "해당 회원의 정보를 삭제한다.", response = UserResult.class)
	@RequestMapping(value = "/deleteUser/{uid}", method = RequestMethod.POST)
	public ResponseEntity<UserResult> deleteUser(@RequestBody int uid) throws Exception {
		logger.info("1-5-------------deleteUser------------------------------" + new Date());
		logger.info("1-5-------------deleteUser------------------------------" + uid);

		User user = userService.getUser(uid);
		UserResult ur = new UserResult();
		if (user == null || user.getEmail() == null || user.getEmail().equals("")) {
			ur.setMsg("회원정보를 가져오는데 실패했습니다.");
			ur.setState("fail");
		} else {
			userService.deleteUser(uid);
			ur.setMsg("회원탈퇴가 성공적으로 처리되었습니다.");
			ur.setState("success");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = "모든 회원의 정보를 반환한다.", response = List.class)
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() throws Exception {
		logger.info("1-6-------------getAllUsers-----------------------------" + new Date());
		logger.info("1-6-------------getAllUsers-----------------------------");
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "회원의 정보를 수정한다.", response = UserResult.class)
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public ResponseEntity<UserResult> updateUser(@RequestBody User model) throws Exception {
		logger.info("1-7-------------updateUser-----------------------------" + new Date());
		logger.info("1-7-------------updateUser-----------------------------" + model);
		User user = userService.getUser(model.getUid());
		UserResult ur = new UserResult();
		if (user == null || user.getEmail() == null || user.getEmail().equals("")) {
			ur.setMsg("회원 수정에 실패했습니다.");
			ur.setState("fail");
		} else if (model.getUname() == null || model.getUname().equals("") || model.getPw() == null
				|| model.getPw().equals("")) {
			ur.setMsg("입력되지 않은 필수값이 있습니다.");
			ur.setState("fail");
		} else {
			userService.updateUser(model);
			user = userService.getUser(model.getUid());
			ur.setUid(user.getUid());
			ur.setUname(user.getUname());
			ur.setEmail(user.getEmail());
			ur.setAuth(user.getAuth());
			ur.setMajor(user.getMajor());
			ur.setUimg(user.getUimg());
			ur.setMsg("성공적으로 회원 수정을 완료했습니다.");
			ur.setState("success");
		}
		return new ResponseEntity<UserResult>(ur, HttpStatus.OK);
	}

}