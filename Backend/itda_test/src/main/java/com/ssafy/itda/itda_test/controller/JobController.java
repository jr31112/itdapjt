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

import com.ssafy.itda.itda_test.help.Result;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.service.IJobService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class JobController {
	public static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private IJobService jobService;

	@ApiOperation(value = "직무를 추가한다.", response = Result.class)
	@RequestMapping(value = "/createJob", method = RequestMethod.POST)
	public ResponseEntity<Result> createJob(@RequestBody Job model) throws Exception {
		logger.info("1-------------createJob-----------------------------" + new Date());
		logger.info("1-------------createJob-----------------------------" + model);
		Result r = new Result();
		if (model.getTo() == null || model.getTo().equals("") || model.getJname() == null || model.getJname().equals("")
				|| model.getWid() == 0) {
			r.setMsg("필수 입력값이 누락되었습니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		jobService.createJob(model);
		r.setMsg("직무 입력이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "직무를 삭제한다.", response = Result.class)
	@RequestMapping(value = "/deleteJob", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteJob(@RequestBody int jid) throws Exception {
		logger.info("2-------------deleteJob-----------------------------" + new Date());
		logger.info("2-------------deleteJob-----------------------------" + jid);
		Result r = new Result();
		Job job = jobService.getJob(jid);
		if (jid == 0 || job == null) {
			r.setMsg("존재하지 않는 jid값입니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		jobService.deleteJob(jid);
		r.setMsg("직무 삭제가 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "직무를 수정한다.", response = Result.class)
	@RequestMapping(value = "/updateJob", method = RequestMethod.PUT)
	public ResponseEntity<Result> updateJob(@RequestBody Job model) throws Exception {
		logger.info("3-------------updateJob-----------------------------" + new Date());
		logger.info("3-------------updateJob-----------------------------" + model);
		Result r = new Result();
		int jid = model.getJid();
		Job job = jobService.getJob(jid);
		if (jid == 0 || job == null) {
			r.setMsg("존재하지 않는 jid값입니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		if (model.getTo() == null || model.getTo().equals("") || model.getJname() == null || model.getJname().equals("")
				|| model.getWid() == 0) {
			r.setMsg("입력되지 않은 필수값이 있습니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		jobService.updateJob(model);
		r.setMsg("직무 수정이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

}
