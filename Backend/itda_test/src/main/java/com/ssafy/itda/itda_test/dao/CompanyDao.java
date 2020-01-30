package com.ssafy.itda.itda_test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Company;

@Repository
public class CompanyDao {
	String ns = "ssafy.user.";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void createCompany(Company model) {
		sqlSession.insert(ns + "createCompany", model);
	}
	
	public void deleteCompany(Integer cid) {
		sqlSession.delete(ns + "deleteCompany", cid);
	}
	
	public void updateCompany(Company model) {
		sqlSession.update(ns + "updateCompany", model);
	}
}
