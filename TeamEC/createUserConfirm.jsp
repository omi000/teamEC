<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録内容確認</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<s:include value="header.jsp" />
	<div id="contents">
		<h1>ユーザー情報入力確認画面</h1>
		<s:form action="CreateUserCompleteAction">
			<table class="vertical-list-table">
				<tr>
					<th scope="row"><s:label value="姓" /></th>
					<td><s:property value="#session.familyName" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="名" /></th>
					<td><s:property value="#session.firstName" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="姓ふりがな" /></th>
					<td><s:property value="#session.familyNameKana" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="名ふりがな" /></th>
					<td><s:property value="#session.firstNameKana" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="性別" /></th>
					<td><s:property value="#session.sex" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="メールアドレス" /></th>
					<td><s:property value="#session.email" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="ユーザーID" />
					<td><s:property value="#session.loginId" />
				</tr>
				<tr>
					<th scope="row"><s:label value="パスワード" />
					<td><s:property value="#session.password" />
				</tr>
			</table>
			<div class="submit_btn_box">
				<div id=".contents-btn-set">
					<s:submit value="登録" class="submit_btn" />
				</div>
			</div>

		</s:form>

		<s:form action="CreateUserAction">
			<div class="submit_btn_box">
				<s:submit value="戻る" class="submit_btn" />
				<s:hidden name="backFlg" value="1" />
			</div>
		</s:form>

	</div>
	<div id="footer">
		<s:include value="footer.jsp" />
	</div>
</body>
</html>