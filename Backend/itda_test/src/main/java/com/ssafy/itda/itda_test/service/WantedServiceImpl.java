package com.ssafy.itda.itda_test.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.ssafy.itda.itda_test.dao.StackDao;
import com.ssafy.itda.itda_test.dao.WantedDao;
import com.ssafy.itda.itda_test.model.Company;
import com.ssafy.itda.itda_test.model.Scrap;
import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Wanted;
import com.ssafy.itda.itda_test.model.WantedStack;

@Service
public class WantedServiceImpl implements IWantedService {

	@Autowired
	private WantedDao wantedDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private StackDao stackDao;

	@Override
	public Wanted getWantedInfo(String wid) {
		// TODO Auto-generated method stub
		return wantedDao.getWantedInfo(wid);
	}

	@Override
	public Company getCompanyInfo(String cid) {
		// TODO Auto-generated method stub
		return wantedDao.getCompanyInfo(cid);
	}

	@Override
	public String getCompanyId(String wid) {
		return wantedDao.getCompanyId(wid);
	}

	@Override
	public List<String> getWantedByRecent() {
		return wantedDao.getWantedByRecent();
	}

	@Override
	public List<String> getWantedByCloseEnd() {
		return wantedDao.getWantedByCloseEnd();
	}

	@Override
	public List<String> getWantedByView() {
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
	public List<String> getWantedAll() {
		return wantedDao.getWantedAll();
	}

	@Override
	public void createWanted(Wanted model) {
		wantedDao.createWanted(model);
	}

	@Override
	public void deleteWanted(String wid) {
		wantedDao.deleteWanted(wid);
	}

	@Override
	public void updateWanted(Wanted model) {
		wantedDao.updateWanted(model);
	}

	@Override
	public List<Stack> getStackInfo(String wid) {
		return wantedDao.getStackInfo(wid);
	}

	@Override
	public List<Stack> getWantedStackInfo(String i) {
		return wantedDao.getWantedStackInfo(i);
	}

	@Override
	public List<String> getWantedByStack(int uid) {
		return wantedDao.getWantedByStack(uid);
	}

	@Override
	public List<String> getWantedByScrap(int uid) {
		return wantedDao.getWantedByScrap(uid);
	}

	@Override
	public void updateVcnt(String wid) {
		wantedDao.updateVcnt(wid);
	}
	
	@Override
	public List<String> getWantedBySearch(String keyword) {
		return wantedDao.getWantedBySearch(keyword);
	}

	@Scheduled(cron = "0 0 0/1 * * *")
	public void schedulerCheckDate() {
		System.out.println("Scheduler Check Expire!!");
		wantedDao.updateCheckOpen();
		wantedDao.updateCheckExpire();
	}

	@Override
	public void callSaramin() throws IOException {
		schedulerSaraminAPI();
	}

	@Scheduled(cron = "0 0 0/5 * * *")
//	@Scheduled(fixedDelay=18000000)
	public void schedulerSaraminAPI() throws IOException {
		System.out.println("Scheduler Saramin API!!");
		String access_key = "0Q5ESrsPZNoxQPN98JpXKSFYmIHImsAyLfHbS2hUMGQUlxZ5O";
		String search_option = "&count=110&job_type=1+4+11&job_category=4&sort=pd&start=";
		for(int i = 0 ; i < 10; i++) {
			String api_url = "https://oapi.saramin.co.kr/job-search/?access-key=" + access_key + search_option;
			
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
			
			ResponseEntity<String> result = restTemplate.exchange(api_url+i, HttpMethod.GET, entity, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(result.getBody());
			JsonNode job = root.findPath("job");
			Iterator<JsonNode> it = job.iterator();
			while (it.hasNext()) {
				inputWanted(it.next());
			}
		}

	}

	private void inputWanted(JsonNode job) throws IOException {
		JsonNode hrefNode = job.get("company").get("detail").get("href");
		if (hrefNode == null) {
			return;
		}
		String href = job.get("company").get("detail").get("href").toString();
		int experience_level = job.get("position").get("experience-level").get("code").intValue();
		if (experience_level != 2) {
			StringTokenizer st = new StringTokenizer(href, "?&");
			String cid = null;
			while (st.hasMoreTokens()) {
				String now = st.nextToken();
				if (now.charAt(0) == 'c') {
					cid = now.substring(4);
					Company company = companyDao.getCompany(cid);
					if (company == null) {
						if (!inputCompany(cid)) {
							return;
						}
					}
					String wid = job.get("id").textValue();
					if (wantedDao.getWantedInfo(wid) != null) {
						return;
					}
					String wantedTitle = job.get("position").get("title").textValue();
					int active = job.get("active").intValue();
					String startDate = job.get("opening-timestamp").textValue();
					String endDate = job.get("expiration-timestamp").textValue();
					String detail_url = "https://www.saramin.co.kr/zf_user/jobs/relay/view-detail?rec_idx=" + wid
							+ "&rec_seq=0";
					Document doc = Jsoup.connect(detail_url).get();
					String detail = doc.getElementsByClass("user_content").html();
					Wanted wanted = new Wanted(wid, wantedTitle, active, startDate, endDate, 0, cid, detail);
					wantedDao.createWanted(wanted);
					checkStack(detail_url, wid);
				}
			}
		}
	}

	private void checkStack(String detail, String wid) throws IOException {
		List<Stack> stacks = stackDao.getAllStacks();
		Document doc = Jsoup.connect(detail).get();
		String[] docs = doc.text().split(" ,/.");
		for (Stack s : stacks) {
			for(String comp : docs) {
				if(s.getTname().equalsIgnoreCase(comp)){
					WantedStack ws = new WantedStack();
					ws.setSid(s.getSid());
					ws.setWid(wid);
					stackDao.createWantedStack(ws);
				}
			}
		}
	}

	private boolean inputCompany(String cid) throws IOException {
		String base_url = "http://www.saramin.co.kr/zf_user/company-info/view?csn=";
		Document doc = Jsoup.connect(base_url + cid).get();
		Company company = new Company();
		company.setCid(cid);
		if (!doc.getElementsByClass("result_txt").isEmpty()) {
			System.out.println("No Company Info");
			return false;
		}
		Elements info_company = doc.getElementsByClass("info_company");
		String corpNm = info_company.get(0).getElementsByClass("name").text();
		company.setCorpNm(corpNm);
		String homepg = info_company.get(0).getElementsByAttribute("href").attr("href");
		String regex = "^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(homepg);
		if (matcher.find()) {
			company.setHomePg(homepg);
		} else {
			company.setHomePg(null);
		}

		Elements list_intro = doc.getElementsByClass("list_intro");
		Elements list_items = list_intro.get(0).getElementsByClass("box");
		for (int i = 0; i < list_items.size(); i++) {
			if (list_items.get(i).text().contains("사원수")) {
				company.setTotPsncnt(list_items.get(i).getElementsByClass("desc").text());
			} else if (list_items.get(i).text().contains("기업형태")) {
				company.setBusiSize(list_items.get(i).getElementsByClass("desc").text());
			} else if (list_items.get(i).text().contains("매출액")) {
				company.setYrSalesAmt(list_items.get(i).getElementsByClass("desc").text());
			}
		}
		
		if(company.getBusiSize() == null ||company.getBusiSize().contains("중소")) {
			return false;
		}

		Elements thumb_company = doc.getElementsByClass("thumb_company");
		company.setLogo(thumb_company.get(0).getElementsByTag("img").attr("src"));

		Elements txt_address = doc.getElementsByClass("txt_address");
		company.setCorpAddr(txt_address.text());

		Elements list_info = doc.getElementsByClass("list_info");
		Elements dts = null;
		Elements dds = null;
		if (list_info.size() != 0) {
			if (list_info.size() > 1) {
				dts = list_info.get(1).getElementsByTag("dt");
				dds = list_info.get(1).getElementsByTag("dd");
			} else {
				dts = list_info.get(0).getElementsByTag("dt");
				dds = list_info.get(0).getElementsByTag("dd");
			}
			for (int i = 0; i < dts.size(); i++) {
				if (dts.get(i).text().contains("업종")) {
					company.setBusiCont(dds.get(i).text());
					break;
				}
			}
		}
		
		companyDao.createCompany(company);
		return true;
	}

}