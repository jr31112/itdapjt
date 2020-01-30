package com.ssafy.itda.itda_test.help;

import java.io.Serializable;

public class CompanyResult implements Serializable{
	private int cid;
	private String corpNm;
	private String totPsncnt;
	private String busiSize;
	private String yrSalesAmt;
	private String corpAddr;
	private String homePg;
	private String busiCont;
	private String logo;
	
	private String msg;
	private String state;
	
	public CompanyResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyResult(int cid, String corpNm, String totPsncnt, String busiSize, String yrSalesAmt, String corpAddr,
			String homePg, String busiCont, String logo, String msg, String state) {
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
		this.msg = msg;
		this.state = state;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CompanyResult [cid=" + cid + ", corpNm=" + corpNm + ", totPsncnt=" + totPsncnt + ", busiSize="
				+ busiSize + ", yrSalesAmt=" + yrSalesAmt + ", corpAddr=" + corpAddr + ", homePg=" + homePg
				+ ", busiCont=" + busiCont + ", logo=" + logo + ", msg=" + msg + ", state=" + state + "]";
	}

}
