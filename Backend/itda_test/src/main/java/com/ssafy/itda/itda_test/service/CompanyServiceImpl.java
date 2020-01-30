package com.ssafy.itda.itda_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.CompanyDao;
import com.ssafy.itda.itda_test.model.Company;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public void createCompany(Company company) {
		companyDao.createCompany(company);
	}
	
	@Override
	public void deleteCompany(Integer cid) {
		companyDao.deleteCompany(cid);
	}
	
	@Override
	public void updateCompany(Integer cid) {
		companyDao.updateCompany(cid);
	}
}
