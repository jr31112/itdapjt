package com.ssafy.itda.itda_test.controller;

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
import com.ssafy.itda.itda_test.service.IStudyGroupService;
import com.ssafy.itda.itda_test.service.IStudyService;
import com.ssafy.itda.itda_test.service.JwtServiceImpl;
import com.ssafy.itda.itda_test.service.StudyServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class StudyController {
	public static final Logger logger = LoggerFactory.getLogger(StudyController.class);

	@Autowired
	private IStudyService studyService;

	@Autowired
	private IStudyGroupService studyGroupService;

	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = "모든 스터디를 조회한다.", response = List.class)
	@RequestMapping(value = "/getAllStudy", method = RequestMethod.GET)
	public ResponseEntity<List<Study>> getAllStury() throws Exception {
		logger.info("1-------------getAllStury-----------------------------" + new Date());
		logger.info("1-------------getAllStury-----------------------------");
		List<Study> studys = studyService.getAllStudy();
		if (studys.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Study>>(studys, HttpStatus.OK);
	}

	@ApiOperation(value = "스터디 상세 내역을 조회한다.", response = StudyResult.class)
	@RequestMapping(value = "/getStudy/{stid}", method = RequestMethod.GET)
	public ResponseEntity<StudyResult> getStudy(@PathVariable int stid) throws Exception {
		logger.info("2-------------getStudy-----------------------------" + new Date());
		logger.info("2-------------getStudy-----------------------------" + stid);
		StudyResult sr = new StudyResult();
		Study s = studyService.getStudy(stid);
		if (stid == 0 || s == null || s.getStname() == null || s.getStname().equals("") || s.getMaxPcnt() == 0
				|| s.getPcnt() == 0 || s.getStype() == 0 || s.getSgroup() == 0 || s.getContent() == null
				|| s.getContent().equals("") || s.getCaptain() == 0) {
			sr.setMsg("회원 정보를 가져오는데 실패하였습니다.");
			sr.setState("Fail");
			return new ResponseEntity<StudyResult>(sr, HttpStatus.OK);
		}
		sr.setStudy(s);
		sr.setMsg("회원 정보를 가져오는데 성공하였습니다.");
		sr.setState("success");
		return new ResponseEntity<StudyResult>(sr, HttpStatus.OK);
	}

	@ApiOperation(value = "스터디를 생성한다.", response = Result.class)
	@RequestMapping(value = "/createStudy", method = RequestMethod.POST)
	public ResponseEntity<Result> createStudy(@RequestBody Study model, HttpServletRequest req) throws Exception {
		logger.info("3-------------createStudy-----------------------------" + new Date());
		logger.info("3-------------createStudy-----------------------------" + model);
		Result r = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(token));
			int captain = (int) resultMap.get("uid");
			if (model.getStname() == null || model.getStname().equals("") || model.getMaxPcnt() == 0
					|| model.getStype() == 0 || model.getSgroup() == 0
					|| model.getContent() == null || model.getContent().equals("")) {
				r.setMsg("필수 입력값이 누락되었습니다.");
				r.setState("fail");
				return new ResponseEntity<Result>(r, HttpStatus.OK);
			}
			model.setCaptain(captain);
			int stid = studyService.createStudy(model);
			StudyGroup sg = new StudyGroup();
			sg.setStid(stid);
			sg.setUid(captain);
			studyGroupService.createStudyGroup(sg);
			r.setMsg("스터디 생성이 성공적으로 완료되었습니다.");
			r.setState("success");
		} else {
			r.setMsg("사용자 정보를 읽어올 수 없습니다.");
			r.setState("fail");
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "스터디를 삭제한다.", response = Result.class)
	@RequestMapping(value = "/deleteStudy/{stid}", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteStudy(@PathVariable int stid) throws Exception {
		logger.info("2-------------deleteStudy-----------------------------" + new Date());
		logger.info("2-------------deleteStudy-----------------------------" + stid);
		Result r = new Result();
		Study s = studyService.getStudy(stid);
		if (stid == 0 || s == null) {
			r.setMsg("존재하지 않는 stid값입니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		studyService.deleteStudy(stid);
		r.setMsg("스터디 삭제가 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

}