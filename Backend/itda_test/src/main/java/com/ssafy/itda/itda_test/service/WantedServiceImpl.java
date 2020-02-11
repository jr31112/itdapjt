package com.ssafy.itda.itda_test.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.itda.itda_test.dao.CompanyDao;
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
	
	@Autowired
	private CompanyDao companyDao;

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

	@Scheduled(cron = "0 0 0/1 * * *")
	public void schedulerCheckDate() {
		System.out.println("Scheduler Check Expire!!");
		wantedDao.updateCheckOpen();
		wantedDao.updateCheckExpire();
	}

	@Scheduled(cron = "0 0/5 * * * *")
	public void schedulerSaraminAPI() throws IOException {
		System.out.println("Scheduler Saramin API!!");
		String access_key = "0Q5ESrsPZNoxQPN98JpXKSFYmIHImsAyLfHbS2hUMGQUlxZ5O";
		String search_option = "&count=110&job_type=1+4+11&job_category=4&sort=pd";
		String api_url = "https://oapi.saramin.co.kr/job-search/?access-key=" + access_key + search_option;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(api_url, HttpMethod.GET, entity, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(result.getBody());
		JsonNode job = root.findPath("job");
		Iterator<JsonNode> it = job.iterator();
		while(it.hasNext()) {
			inputWanted(it.next());
		}

//		Document doc = Jsoup.connect(api_url).get();
//		Elements sri_section = doc.select("div.sri_section");
//		System.out.println(doc.toString());
	}

	private void inputWanted(JsonNode job) throws IOException {
		String href = job.path("company").path("detail").get("href").toString();
		if(href == null) {
			return;
		}
		StringTokenizer st = new StringTokenizer(href, "?&");
		String cid = null;
		while(st.hasMoreTokens()) {
			String now = st.nextToken();
			if(now.charAt(0) == 'c') {
				cid = now.substring(4);
				Company company = companyDao.getCompany(cid);
				if(company == null) {
					if(!inputCompany(cid)) {
						return;
					}
				}
			}
		}
		
	}

	private boolean inputCompany(String cid) throws IOException {
		String base_url = "http://www.saramin.co.kr/zf_user/company-info/view?csn=";
		String back_url = "&utm_source=job-search-api&utm_medium=api&utm_campaign=saramin-job-search-api";
		Document doc = Jsoup.connect(base_url + cid + back_url).get();
		if(doc.getElementsByClass("result_txt").size() == 0) {
			return false;
		}
		Elements corpNmElem = doc.getElementsByClass("info_company");
		return true;
	}
	
	
}
