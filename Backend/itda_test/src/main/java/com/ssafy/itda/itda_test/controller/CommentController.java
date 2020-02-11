package com.ssafy.itda.itda_test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.ssafy.itda.itda_test.help.CompanyResult;
import com.ssafy.itda.itda_test.help.Result;
import com.ssafy.itda.itda_test.help.UserResult;
import com.ssafy.itda.itda_test.help.WantedResult;
import com.ssafy.itda.itda_test.model.Comment;
import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.User;
import com.ssafy.itda.itda_test.model.Wanted;
import com.ssafy.itda.itda_test.service.ICommentService;
import com.ssafy.itda.itda_test.service.ICompanyService;
import com.ssafy.itda.itda_test.service.IUserService;
import com.ssafy.itda.itda_test.service.IWantedService;
import com.ssafy.itda.itda_test.service.UserServiceImpl;
import com.ssafy.itda.itda_test.service.WantedServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class CommentController {
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private IUserService userService;

	@ApiOperation(value = "댓글을 작성한다.", response = Result.class)
	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public ResponseEntity<Result> createJob(@RequestBody Comment model) throws Exception {
		logger.info("1-------------createComment-----------------------------" + new Date());
		logger.info("1-------------createComment-----------------------------" + model);
		Result r = new Result();
		int uid = model.getUid();
		int wid = model.getWid();
		if (uid == 0 || wid == 0 || model.getContent() == null || model.getContent().equals("")) {
			r.setMsg("필수 입력값이 누락되었습니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		User u = userService.getUser(uid);
		if (u == null) {
			r.setMsg("해당 uid를 가진 user를 찾을 수 없습니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		model.setWriter(u.getUname());
		commentService.createComment(model);
		r.setMsg("댓글 작성이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글을 삭제한다.", response = Result.class)
	@RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteComment(@RequestBody int cmid) throws Exception {
		logger.info("2-------------deleteComment-----------------------------" + new Date());
		logger.info("2-------------deleteComment-----------------------------" + cmid);
		Result r = new Result();
		Comment cmt = commentService.getComment(cmid);
		if (cmid == 0 || cmt == null) {
			r.setMsg("존재하지 않는 cmid값입니다.");
			r.setState("fail");
		} else {
			commentService.deleteComment(cmid);
			r.setMsg("댓글 삭제가 성공적으로 완료되었습니다.");
			r.setState("success");
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글을 수정한다.", response = Result.class)
	@RequestMapping(value = "/updateComment", method = RequestMethod.PUT)
	public ResponseEntity<Result> updateComment(@RequestBody Comment model) throws Exception {
		logger.info("3-------------updateComment-----------------------------" + new Date());
		logger.info("3-------------updateComment-----------------------------" + model);
		Result r = new Result();
		int cmid = model.getCmid();
		Comment cmt = commentService.getComment(cmid);
		if (cmid == 0 || cmt == null) {
			r.setMsg("존재하지 않는 cmid값입니다.");
			r.setState("fail");
		} else if (model.getContent() == null || model.getContent().equals("")) {
			r.setMsg("입력되지 않은 필수값이 있습니다.");
			r.setState("fail");
		} else {
			commentService.updateComment(model);
			r.setMsg("댓글 수정이 성공적으로 완료되었습니다.");
			r.setState("success");
		}
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}
}
