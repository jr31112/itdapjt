package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Wanted;

@Repository
public class WantedDao {

	String ns = "ssafy.wanted.";

	@Autowired
	private SqlSession sqlSession;

	public Wanted getWantedInfo(int wid) {
		return sqlSession.selectOne(ns + "getWantedInfo", wid);
	}

	public Company getCompanyInfo(int cid) {
		return sqlSession.selectOne(ns + "getCompanyInfo", cid);
	}

	public List<Job> getJobsInfo(int wid) {
		return sqlSession.selectList(ns + "getJobsInfo", wid);
	}

	public int getCompanyId(int wid) {
		return sqlSession.selectOne(ns + "getCompanyId", wid);
	}

	public List<Integer> getWantedByRecent() {
		return sqlSession.selectList(ns + "getWantedByRecent");
	}

	public List<Integer> getWantedByCloseEnd() {
		return sqlSession.selectList(ns + "getWantedByCloseEnd");
	}

	public List<Integer> getWantedByView() {
		return sqlSession.selectList(ns + "getWantedByView");
	}

	public Scrap isScraped(Scrap model) {
		return sqlSession.selectOne(ns + "isScraped", model);
	}

	public void unScrap(Scrap model) {
		sqlSession.delete(ns + "unScrap", model);
	}

	public void scrap(Scrap model) {
		sqlSession.insert(ns + "scrap", model);
	}

	public List<Integer> getWantedAll() {
		return sqlSession.selectList(ns + "getWantedAll");
	}
}
