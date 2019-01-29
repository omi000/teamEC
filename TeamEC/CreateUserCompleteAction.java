package com.internousdev.anemone.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.anemone.dao.UserInfoDAO;
import com.internousdev.anemone.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware{

	private String loginId;
	private String password;



	private Map<String,Object> session;

	public String execute(){

		String result=ERROR;
		String sex=null;
		 List<String> familyNameErrorMessageList = new ArrayList<String>();
		 List<String> firstNameErrorMessageList = new ArrayList<String>();
		 List<String> familyNameKanaErrorMessageList = new ArrayList<String>();
		 List<String> firstNameKanaErrorMessageList = new ArrayList<String>();
		 List<String> emailErrorMessageList = new ArrayList<String>();
		 List<String> loginIdErrorMessageList = new ArrayList<String>();
		 List<String> passwordErrorMessageList = new ArrayList<String>();

//		セッションタイムアウト処理
		if(!session.containsKey("mCategoryList")) {
			return "sessionError";
		}

		try{
		loginId = session.get("loginId").toString();
		password = session.get("password").toString();
		}catch(NullPointerException e){
			return ERROR;
		}
//		エラーチェック(文字数、文字種)
		InputChecker inputChecker = new InputChecker();
		familyNameErrorMessageList = inputChecker.doCheck("姓", (session.get("familyName").toString()), 1, 16, true, true, true, false, false, false, false, false, false);
		firstNameErrorMessageList = inputChecker.doCheck("名", (session.get("firstName").toString()), 1, 16, true, true, true, false, false, false, false, false, false);
		familyNameKanaErrorMessageList = inputChecker.doCheck("姓ふりがな", (session.get("familyNameKana").toString()), 1, 16, false, false, true, false, false, false, false, false, false);
		firstNameKanaErrorMessageList = inputChecker.doCheck("名ふりがな", (session.get("firstNameKana").toString()), 1, 16, false, false, true, false, false, false, false, false, false);
		emailErrorMessageList = inputChecker.doCheck("メールアドレス", (session.get("email").toString()), 10, 32, true, false, false, true, true, false, false, false, false);
		loginIdErrorMessageList = inputChecker.doCheck("ユーザーID", (session.get("loginId").toString()), 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード", (session.get("password").toString()), 1, 16, true, false, false, true, false, false, false, false, false);

//		エラーが1つでもあればERROR画面に遷移する
		if(familyNameErrorMessageList.size() > 0
				||firstNameErrorMessageList.size() > 0
				||familyNameKanaErrorMessageList.size() > 0
				||firstNameKanaErrorMessageList.size() > 0
				||emailErrorMessageList.size() > 0
				||loginIdErrorMessageList.size() > 0
				||passwordErrorMessageList.size() > 0){
				return ERROR;
			}

//		DBに同じIDが存在しないかのチェック

			UserInfoDAO userInfoDAO=new UserInfoDAO();
			int count = userInfoDAO.getUserId(session.get("loginId").toString());

			if (!(count == 0)) {

				return ERROR;
			}

//		DBに性別を登録する際に0か1になるようにする。

			if(session.get("sex").equals("男性")){
				sex="0";
			}
			else{
				sex="1";
			}

//		登録した件数をcountに入れる
		int countA=userInfoDAO.createUser((session.get("familyName")).toString(),(session.get("firstName")).toString(),(session.get("familyNameKana")).toString(),(session.get("firstNameKana")).toString(),sex,(session.get("email")).toString(),(session.get("loginId")).toString(),(session.get("password")).toString());
		if(countA>0){

			return SUCCESS;
		}
		return result;
	}


	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
