package com.ssafy.itda.itda_test.help;

import java.io.Serializable;
import java.util.List;

import com.ssafy.itda.itda_test.model.Stack;
import com.ssafy.itda.itda_test.model.Study;
import com.ssafy.itda.itda_test.model.User;

public class UserResult implements Serializable {

	// User Model
	private User user;

	// Stack Model
	private List<Stack> mystacks;

	// Scrap Model
	private List<WantedResult> myScrapWanteds;
	private List<WantedResult> myEndedScrapWanteds;

	// Study Model
	private List<Study> myStudies;

	// Response result
	private String msg;
	private String state;
	private String isSocial;

	public UserResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Stack> getMystacks() {
		return mystacks;
	}

	public void setMystacks(List<Stack> mystacks) {
		this.mystacks = mystacks;
	}

	public List<WantedResult> getMyEndedScrapWanteds() {
		return myEndedScrapWanteds;
	}

	public void setMyEndedScrapWanteds(List<WantedResult> myEndedScrapWanteds) {
		this.myEndedScrapWanteds = myEndedScrapWanteds;
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

	public List<WantedResult> getMyScrapWanteds() {
		return myScrapWanteds;
	}

	public void setMyScrapWanteds(List<WantedResult> myScrapWanteds) {
		this.myScrapWanteds = myScrapWanteds;
	}

	public List<Study> getMyStudies() {
		return myStudies;
	}

	public void setMyStudies(List<Study> myStudies) {
		this.myStudies = myStudies;
	}

	public String getIsSocial() {
		return isSocial;
	}

	public void setIsSocial(String isSocial) {
		this.isSocial = isSocial;
	}

	@Override
	public String toString() {
		return "UserResult [user=" + user + ", mystacks=" + mystacks + ", myScrapWanteds=" + myScrapWanteds
				+ ", myEndedScrapWanteds=" + myEndedScrapWanteds + ", myStudies=" + myStudies + ", msg=" + msg
				+ ", state=" + state + ", isSocial=" + isSocial + "]";
	}

}
