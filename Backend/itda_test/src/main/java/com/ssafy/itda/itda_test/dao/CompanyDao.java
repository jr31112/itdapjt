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
	
	public void createCompany(Company company) {
		sqlSession.insert(ns + "createCompany", company);
	}
	
	public void deleteCompany(Integer cid) {
		sqlSession.delete(ns + "deleteCompany", cid);
	}
	
	public void updateCompany(Integer cid) {
		sqlSession.update(ns + "updateCompany", cid);
	}
}
