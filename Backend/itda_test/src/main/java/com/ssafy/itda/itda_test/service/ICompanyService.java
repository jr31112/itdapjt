package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.Company;

public interface ICompanyService {
	void createCompany(Company model);
	void deleteCompany(String cid);
	void updateCompany(Company model);
	List<Company> getAllCompanys();		
}




