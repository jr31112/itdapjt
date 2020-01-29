package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.Company;

public interface ICompanyService {
	public void createCompany(Company company);
	public void deleteCompany(Integer cid);
	public void updateCompany(Integer cid);
	
		
}
