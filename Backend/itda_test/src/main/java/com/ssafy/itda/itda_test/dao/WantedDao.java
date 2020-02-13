package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Wanted;

@Repository
public class WantedDao {

	String ns = "ssafy.wanted.";

	@Autowired
	private SqlSession sqlSession;

	public Wanted getWantedInfo(String wid) {
		return sqlSession.selectOne(ns + "getWantedInfo", wid);
	}

	public Company getCompanyInfo(String cid) {
		return sqlSession.selectOne(ns + "getCompanyInfo", cid);
	}

	public String getCompanyId(String wid) {
		return sqlSession.selectOne(ns + "getCompanyId", wid);
	}

	public List<String> getWantedByRecent() {
		return sqlSession.selectList(ns + "getWantedByRecent");
	}

	public List<String> getWantedByCloseEnd() {
		return sqlSession.selectList(ns + "getWantedByCloseEnd");
	}

	public List<String> getWantedByView() {
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

	public List<String> getWantedAll() {
		return sqlSession.selectList(ns + "getWantedAll");
	}

	public void createWanted(Wanted model) {
		sqlSession.insert(ns + "createWanted", model);
	}

	public void deleteWanted(String wid) {
		sqlSession.delete(ns + "deleteWanted", wid);
	}

	public void updateWanted(Wanted model) {
		sqlSession.update(ns + "updateWanted", model);
	}

	public List<Stack> getStackInfo(String wid) {
		return sqlSession.selectList(ns + "getStackInfo", wid);
	}

	public List<Stack> getWantedStackInfo(String i) {
		return sqlSession.selectList(ns + "getWantedStackInfo", i);
	}

	public List<String> getWantedByStack(int uid) {
		return sqlSession.selectList(ns + "getWantedByStack", uid);
	}

	public List<String> getWantedByScrap(int uid) {
		return sqlSession.selectList(ns + "getWantedByScrap", uid);
	}

	public void updateVcnt(String wid) {
		sqlSession.update(ns + "updateVcnt", wid);
	}

	public void updateCheckExpire() {
		sqlSession.update(ns + "updateCheckExpire");
	}

	public void updateCheckOpen() {
		sqlSession.update(ns + "updateCheckOpen");
	}
	
	public List<String> getWantedBySearch(String keyword) {
		return sqlSession.selectList(ns + "getWantedBySearch", keyword);
	}
}
