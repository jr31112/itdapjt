package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Wanted;

public interface IWantedService {
	int getCompanyId(int wid);
	Wanted getWantedInfo(int wid);
	Company getCompanyInfo(int cid);
	List<Job> getJobsInfo(int wid);
	List<Integer> getWantedByRecent();
	List<Integer> getWantedByCloseEnd();
	List<Integer> getWantedByView();
	Scrap isScraped(Scrap model);
	void unScrap(Scrap model);
	void scrap(Scrap model);
	List<Integer> getWantedAll();
	void createWanted(Wanted model);
	void deleteWanted(Integer wid);
	void updateWanted(Wanted model);
}
