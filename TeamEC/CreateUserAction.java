package com.internousdev.anemone.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware{
	private static final String MALE = "男性";
	private static final String FEMALE = "女性";
	private Map<String, Object> session;
	private String backFlg;

	public String execute() {

//		セッションタイムアウト
		if(!session.containsKey("mCategoryList")) {
			return "sessionError";
		}
		List<String> sexList = new ArrayList<String>();
		sexList.add(MALE);
		sexList.add(FEMALE);
		session.put("sexList", sexList);


		if(backFlg==null){
		session.remove("familyName");
		session.remove("firstName");
		session.remove("familyNameKana");
		session.remove("firstNameKana");
		session.put("sex", MALE);
		session.remove("email");
		session.remove("loginId");
		session.remove("password");

		}

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getBackFlg() {
		return backFlg;
	}

	public void setBackFlg(String backFlg) {
		this.backFlg = backFlg;
	}

}
