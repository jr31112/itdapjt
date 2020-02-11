package com.ssafy.itda.itda_test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.ssafy.itda.itda_test.model.WantedStack;
import com.ssafy.itda.itda_test.model.MyStack;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.service.IStackService;
import com.ssafy.itda.itda_test.service.JwtServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class StackController {
	public static final Logger logger = LoggerFactory.getLogger(StackController.class);

	@Autowired
	private IStackService stackService;

	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = "기술스택을 추가한다.", response = Result.class)
	@RequestMapping(value = "/createStack", method = RequestMethod.POST)
	public ResponseEntity<Result> createStack(@RequestBody String tname) throws Exception {
		logger.info("1-------------createStack-----------------------------" + new Date());
		logger.info("1-------------createStack-----------------------------" + tname);
		Result r = new Result();
		if (tname.equals("") || tname == null) {
			r.setMsg("필수 입력값이 누락되었습니다.");
			r.setState("fail");
			return new ResponseEntity<Result>(r, HttpStatus.OK);
		}
		stackService.createStack(tname);
		r.setMsg("기술스택 입력이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "나의 기술스택을 추가한다.", response = Result.class)
	@RequestMapping(value = "/createMyStack", method = RequestMethod.POST)
	public ResponseEntity<Result> createMyStack(@RequestBody MyStack ms) throws Exception {
		logger.info("1-------------createMyStack-----------------------------" + new Date());
		logger.info("1-------------createMyStack-----------------------------" + ms);
		Result r = new Result();
		List<Stack> myStacks = stackService.getMyStacks(ms.getUid());
		// 새로 입력한 기술스택이 없으면 넣어준다.
		for (int i : ms.getStacks()) {
			boolean is_in_mystacks = false;
			for (Stack s : myStacks) {
				if (i == s.getSid()) {
					is_in_mystacks = true;
					break;
				}
			}
			if (!is_in_mystacks) {
				MyStack newms = new MyStack();
				newms.setUid(ms.getUid());
				newms.setSid(i);
				stackService.createMyStack(newms);
			}
		}
		// 내 기술스택에서 새로 입력한 기술 스택이 없으면 빼준다.
		List<Integer> myStacks_sid = new LinkedList<>();
		for (Stack s : myStacks) {
			myStacks_sid.add(s.getSid());
		}
		for (int i : myStacks_sid) {
			if (!ms.getStacks().contains(i)) {
				MyStack newms = new MyStack();
				newms.setUid(ms.getUid());
				newms.setSid(i);
				stackService.deleteMyStack(newms);
			}
		}
		r.setMsg("기술스택 입력이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "공고별 기술스택을 추가한다.", response = Result.class)
	@RequestMapping(value = "/createWantedStack", method = RequestMethod.POST)
	public ResponseEntity<Result> createWantedStack(@RequestBody WantedStack ws) throws Exception {
		logger.info("1-------------createWantedStack-----------------------------" + new Date());
		logger.info("1-------------createWantedStack-----------------------------" + ws);
		Result r = new Result();
		String wid = ws.getWid();
		for (int i : ws.getStacks()) {
			WantedStack newws = new WantedStack();
			newws.setWid(wid);
			newws.setSid(i);
			stackService.createWantedStack(newws);
		}
		r.setMsg("기술스택 입력이 성공적으로 완료되었습니다.");
		r.setState("success");
		return new ResponseEntity<Result>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "전체 스택리스트를 확인한다.", response = List.class)
	@RequestMapping(value = "/getAllStacks", method = RequestMethod.GET)
	public ResponseEntity<List<Stack>> getAllStacks() throws Exception {
		logger.info("5-------------getAllStacks-----------------------------" + new Date());
		List<Stack> stack_list = stackService.getAllStacks();
		return new ResponseEntity<List<Stack>>(stack_list, HttpStatus.OK);
	}

	@ApiOperation(value = "나의 스택리스트를 확인한다.", response = List.class)
	@RequestMapping(value = "/getMyStacks", method = RequestMethod.GET)
	public ResponseEntity<List<Stack>> getMyStacks(HttpServletRequest req) throws Exception {
		logger.info("5-------------getMyStacks-----------------------------" + new Date());
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			int userid = (int) resultMap.get("uid");
			List<Stack> my_list = stackService.getMyStacks(userid);
			return new ResponseEntity<List<Stack>>(my_list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "공고별 스택리스트를 확인한다.", response = List.class)
	@RequestMapping(value = "/getWantedStacks/{wid}", method = RequestMethod.GET)
	public ResponseEntity<List<Stack>> getMyStacks(@PathVariable String wid) throws Exception {
		logger.info("5-------------getMyStacks-----------------------------" + new Date());
		List<Stack> stack_list = stackService.getWantedStacks(wid);
		return new ResponseEntity<List<Stack>>(stack_list, HttpStatus.OK);
	}
}
