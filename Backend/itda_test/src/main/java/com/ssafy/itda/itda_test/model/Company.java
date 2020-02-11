package com.ssafy.itda.itda_test.model;

import java.io.Serializable;

public class Company implements Serializable {
	private String cid;
	private String corpNm;
	private String totPsncnt;
	private String busiSize;
	private String yrSalesAmt;
	private String corpAddr;
	private String homePg;
	private String busiCont;
	private String logo;

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(String cid, String corpNm, String totPsncnt, String busiSize, String yrSalesAmt, String corpAddr,
			String homePg, String busiCont, String logo) {
		super();
		this.cid = cid;
		this.corpNm = corpNm;
		this.totPsncnt = totPsncnt;
		this.busiSize = busiSize;
		this.yrSalesAmt = yrSalesAmt;
		this.corpAddr = corpAddr;
		this.homePg = homePg;
		this.busiCont = busiCont;
		this.logo = logo;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCorpNm() {
		return corpNm;
	}

	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}

	public String getTotPsncnt() {
		return totPsncnt;
	}

	public void setTotPsncnt(String totPsncnt) {
		this.totPsncnt = totPsncnt;
	}

	public String getBusiSize() {
		return busiSize;
	}

	public void setBusiSize(String busiSize) {
		this.busiSize = busiSize;
	}

	public String getYrSalesAmt() {
		return yrSalesAmt;
	}

	public void setYrSalesAmt(String yrSalesAmt) {
		this.yrSalesAmt = yrSalesAmt;
	}

	public String getCorpAddr() {
		return corpAddr;
	}

	public void setCorpAddr(String corpAddr) {
		this.corpAddr = corpAddr;
	}

	public String getHomePg() {
		return homePg;
	}

	public void setHomePg(String homePg) {
		this.homePg = homePg;
	}

	public String getBusiCont() {
		return busiCont;
	}

	public void setBusiCont(String busiCont) {
		this.busiCont = busiCont;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "Company [cid=" + cid + ", corpNm=" + corpNm + ", totPsncnt=" + totPsncnt + ", busiSize=" + busiSize
				+ ", yrSalesAmt=" + yrSalesAmt + ", corpAddr=" + corpAddr + ", homePg=" + homePg + ", busiCont="
				+ busiCont + ", logo=" + logo + "]";
	}

}
