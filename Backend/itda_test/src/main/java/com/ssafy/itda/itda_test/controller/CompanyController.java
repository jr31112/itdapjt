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
import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.User;
import com.ssafy.itda.itda_test.model.Wanted;
import com.ssafy.itda.itda_test.service.ICompanyService;
import com.ssafy.itda.itda_test.service.IUserService;
import com.ssafy.itda.itda_test.service.IWantedService;
import com.ssafy.itda.itda_test.service.WantedServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class CompanyController {
	public static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IWantedService wantedService;

	@ApiOperation(value = "새로운 기업의 정보를 입력한다.", response = CompanyResult.class)
	@RequestMapping(value = "/CreateCompany", method = RequestMethod.POST)
	public ResponseEntity<CompanyResult> createCompany(@RequestBody Company model) throws Exception {
		logger.info("2-1-------------createCompany-----------------------------" + new Date());
		logger.info("2-1-------------createCompany-----------------------------" + model);
		CompanyResult cr = new CompanyResult();
		if (model.getCorpNm() == null || model.getCorpNm().equals("")) {
			cr.setMsg("기업명이 누락되었습니다.");
			cr.setState("fail");
			return new ResponseEntity<CompanyResult>(cr, HttpStatus.OK);
		}
		companyService.createCompany(model);
		cr.setMsg("기업 입력이 성공적으로 완료되었습니다.");
		cr.setState("success");
		return new ResponseEntity<CompanyResult>(cr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 기업 정보를 삭제한다.", response = CompanyResult.class)
	@RequestMapping(value = "/deleteCompany/{cid}", method = RequestMethod.DELETE)
	public ResponseEntity<CompanyResult> deleteCompany(@RequestBody String cid) throws Exception {
		logger.info("2-2-------------deleteCompany-----------------------------" + new Date());
		logger.info("2-2-------------deleteCompany-----------------------------" + cid);
		CompanyResult cr = new CompanyResult();
		Company company = wantedService.getCompanyInfo(cid);
		if (cid == null || cid.equals("") || company == null) {
			cr.setMsg("존재하지 않는 cid값이 입력되었습니다.");
			cr.setState("fail");
			return new ResponseEntity<CompanyResult>(cr, HttpStatus.OK);
		}
		companyService.deleteCompany(cid);
		cr.setMsg("기업 삭제가 성공적으로 이루어졌습니다.");
		cr.setState("success");
		return new ResponseEntity<CompanyResult>(cr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 기업 정보를 수정한다.", response = CompanyResult.class)
	@RequestMapping(value = "/updateCompany", method = RequestMethod.PUT)
	public ResponseEntity<CompanyResult> updateCompany(@RequestBody Company model) throws Exception {
		logger.info("2-3-------------updateCompany-----------------------------" + new Date());
		logger.info("2-3-------------updateCompany-----------------------------" + model);
		CompanyResult cr = new CompanyResult();
		String cid = model.getCid();
		Company company = wantedService.getCompanyInfo(cid);
		if (cid == null || cid.equals("") || company == null) {
			cr.setMsg("존재하지 않는 cid값 입니다.");
			cr.setState("fail");
		} else if (model.getCorpNm() == null || model.getCorpNm().equals("")) {
			cr.setMsg("잘못된 기업명이 입력되었습니다.");
			cr.setState("fail");
		} else {
			companyService.updateCompany(model);
			cr.setMsg("기업 수정이 성공적으로 이루어졌습니다.");
			cr.setState("success");
		}
		return new ResponseEntity<CompanyResult>(cr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "전체 기업리스트를 확인한다.", response = List.class)
	@RequestMapping(value = "/getAllCompanys", method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getAllCompanys() throws Exception {
		logger.info("5-------------getAllCompanys-----------------------------" + new Date());
		List<Company> companyList = companyService.getAllCompanys();
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
	}
}
