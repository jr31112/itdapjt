package com.ssafy.itda.itda_test.help;

import java.io.Serializable;

public class UserResult implements Serializable {

	// User Model
	private int uid;
	private String email;
	private String pw;
	private String uname;
	private int auth;
	private String major;
	private String uimg;
	private int cid;

	// Stack Model

	// Response result
	private String msg;
	private String state;

	public UserResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResult(int uid, String email, String pw, String uname, int auth, String major, String uimg, int cid,
			String msg, String state) {
		super();
		this.uid = uid;
		this.email = email;
		this.pw = pw;
		this.uname = uname;
		this.auth = auth;
		this.major = major;
		this.uimg = uimg;
		this.cid = cid;
		this.msg = msg;
		this.state = state;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getUimg() {
		return uimg;
	}

	public void setUimg(String uimg) {
		this.uimg = uimg;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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
		return "UserResult [uid=" + uid + ", email=" + email + ", pw=" + pw + ", uname=" + uname + ", auth=" + auth
				+ ", major=" + major + ", uimg=" + uimg + ", cid=" + cid + ", msg=" + msg + ", state=" + state + "]";
	}

}
