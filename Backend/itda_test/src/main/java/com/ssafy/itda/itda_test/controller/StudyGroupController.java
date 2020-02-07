package com.ssafy.itda.itda_test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.itda.itda_test.help.Result;
import com.ssafy.itda.itda_test.help.StudyResult;
import com.ssafy.itda.itda_test.help.UserResult;
import com.ssafy.itda.itda_test.model.Study;
import com.ssafy.itda.itda_test.model.StudyGroup;
import com.ssafy.itda.itda_test.model.User;
import com.ssafy.itda.itda_test.service.IStudyGroupService;
import com.ssafy.itda.itda_test.service.IStudyService;
import com.ssafy.itda.itda_test.service.IUserService;
import com.ssafy.itda.itda_test.service.JwtServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class StudyGroupController {
	public static final Logger logger = LoggerFactory.getLogger(StudyGroupController.class);

	@Autowired
	private IStudyGroupService studyGroupService;

	@Autowired
	private IUserService userService;

	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = "스터디 멤버를 조회한다.", response = List.class)
	@RequestMapping(value = "/getStudyGroup/{stid}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getStudyGroup(@PathVariable int stid) throws Exception {
		logger.info("1-------------getStudyGroup-----------------------------" + new Date());
		logger.info("1-------------getStudyGroup-----------------------------" + stid);
		StudyResult sr = new StudyResult();
		List<User> users = new ArrayList<>();
		List<StudyGroup> sdList = studyGroupService.getStudyGroup(stid);
		for (StudyGroup sd : sdList) {
			int uid = sd.getUid();
			users.add(userService.getUser(uid));
		}
		if (users.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "스터디에 가입한다.", response = Result.class)
	@RequestMapping(value = "/createStudyGroup", method = RequestMethod.POST)
	public ResponseEntity<Result> createStudyGroup(@RequestBody StudyGroup model, HttpServletRequest req)
			throws Exception {
		logger.info("2-------------createStudyGroup-----------------------------" + new Date());
		logger.info("2-------------createStudyGroup-----------------------------" + model);
		Result r = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(token));
			int uid = (int) resultMap.get("uid");
			int stid = model.getStid();
			if (uid == 0 || stid == 0) {
				r.setMsg("필수 입력값이 누락되었습니다.");
				r.setState("fail");
				return new ResponseEntity<Result>(r, HttpStatus.OK);
			}
			StudyGroup sg = new StudyGroup();
			sg.setStid(stid);
			sg.setUid(uid);
			if (studyGroupService.getStudyGroupRel(sg) != null) {
				r.setMsg("이미 가입된 스터디입니다.");
				r.setState("fail");
				return new ResponseEntity<Result>(r, HttpStatus.OK);
			}
			studyGroupService.createStudyGroup(sg);
			r.setMsg("스터디 가입 성공적으로 완료되었습니다.");
			r.setState("success");
		} else {
			r.setMsg("사용자 정보를 조회할 수 없습니다.");
			r.setState("fail");
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "스터디를 탈퇴한다.", response = Result.class)
	@RequestMapping(value = "/deleteStudyGroup", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteStudyGroup(@RequestBody int stid, HttpServletRequest req) throws Exception {
		logger.info("3-------------deleteStudyGroup-----------------------------" + new Date());
		logger.info("3-------------deleteStudyGroup-----------------------------" + stid);
		Result r = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(token));
			int uid = (int) resultMap.get("uid");
			StudyGroup sg = new StudyGroup();
			sg.setStid(stid);
			sg.setUid(uid);
			List<StudyGroup> sdList = studyGroupService.getStudyGroup(stid);
			if (sdList.contains(sg)) {
				studyGroupService.deleteStudyGroup(sg);
				r.setMsg("스터디 탈퇴가 성공적으로 완료되었습니다.");
				r.setState("success");
			} else {
				r.setMsg("가입하지 않은 스터디입니다.");
				r.setState("fail");
			}
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}
}
