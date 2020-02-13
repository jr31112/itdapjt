package com.ssafy.itda.itda_test.service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Wanted;

public interface IWantedService {
	String getCompanyId(String wid);
	Wanted getWantedInfo(String wid);
	Company getCompanyInfo(String cid);
	List<String> getWantedByRecent();
	List<String> getWantedByCloseEnd();
	List<String> getWantedByView();
	Scrap isScraped(Scrap model);
	void unScrap(Scrap model);
	void scrap(Scrap model);
	List<String> getWantedAll();
	void createWanted(Wanted model);
	void deleteWanted(String wid);
	void updateWanted(Wanted model);
	List<Stack> getStackInfo(String wid);
	List<Stack> getWantedStackInfo(String i);
	List<String> getWantedByStack(int uid);
	List<String> getWantedByScrap(int uid);
	void updateVcnt(String wid);
	void callSaramin() throws IOException;
	List<String> getWantedBySearchFullText(String keyword);
	List<String> getWantedBySearchLike(String likeKeyword);
}
