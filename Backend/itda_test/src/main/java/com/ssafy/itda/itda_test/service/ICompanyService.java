package com.ssafy.itda.itda_test.service;

import com.ssafy.itda.itda_test.model.Company;

public interface ICompanyService {
	public void createCompany(Company model);
	public void deleteCompany(Integer cid);
	public void updateCompany(Company model);
	
		
}
