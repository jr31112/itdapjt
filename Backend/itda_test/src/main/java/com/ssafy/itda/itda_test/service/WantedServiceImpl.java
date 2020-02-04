package com.ssafy.itda.itda_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.WantedDao;
import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Job;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Wanted;

@Service
public class WantedServiceImpl implements IWantedService {

	@Autowired
	private WantedDao wantedDao;

	@Override
	public Wanted getWantedInfo(int wid) {
		// TODO Auto-generated method stub
		return wantedDao.getWantedInfo(wid);
	}

	@Override
	public Company getCompanyInfo(int cid) {
		// TODO Auto-generated method stub
		return wantedDao.getCompanyInfo(cid);
	}

	@Override
	public List<Job> getJobsInfo(int wid) {
		// TODO Auto-generated method stub
		return wantedDao.getJobsInfo(wid);
	}

	@Override
	public int getCompanyId(int wid) {
		return wantedDao.getCompanyId(wid);
	}

	@Override
	public List<Integer> getWantedByRecent() {
		return wantedDao.getWantedByRecent();
	}

	@Override
	public List<Integer> getWantedByCloseEnd() {
		return wantedDao.getWantedByCloseEnd();
	}

	@Override
	public List<Integer> getWantedByView() {
		return wantedDao.getWantedByView();
	}

	@Override
	public Scrap isScraped(Scrap model) {
		return wantedDao.isScraped(model);
	}

	@Override
	public void unScrap(Scrap model) {
		wantedDao.unScrap(model);
	}

	@Override
	public void scrap(Scrap model) {
		wantedDao.scrap(model);
	}

	@Override
	public List<Integer> getWantedAll() {
		return wantedDao.getWantedAll();
	}

	@Override
	public int createWanted(Wanted model) {
		return wantedDao.createWanted(model);
	}

	@Override
	public void deleteWanted(int wid) {
		wantedDao.deleteWanted(wid);
	}

	@Override
	public void updateWanted(Wanted model) {
		wantedDao.updateWanted(model);
	}

	@Override
	public List<Stack> getStackInfo(int jid) {
		return wantedDao.getStackInfo(jid);
	}

	@Override
	public List<Stack> getWantedStackInfo(int i) {
		return wantedDao.getWantedStackInfo(i);
	}

	@Override
	public List<Integer> getWantedByStack(int uid) {
		return wantedDao.getWantedByStack(uid);
	}

	@Override
	public List<Integer> getWantedByScrap(int uid) {
		return wantedDao.getWantedByScrap(uid);
	}

	@Override
	public void updateVcnt(int wid) {
		wantedDao.updateVcnt(wid);
	}
}
