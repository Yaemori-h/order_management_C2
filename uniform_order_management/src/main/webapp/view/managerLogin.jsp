<!--
管理者用ログイン

作成日：2024/6/21
作成者：南部優実
-->
<%@page contentType="text/html; charset=UTF-8"%>

<%
String managerid = ""; //ユーザーを管理する変数
String managerpassword = ""; //パスワードを管理する変数

Cookie[] managerCookie = request.getCookies(); //クッキーの取得
String error = (String) request.getAttribute("error"); //エラーメッセージの取得

//クッキーがあるか判定
if (managerCookie != null) {
	for (int i = 0; i < managerCookie.length; i++) {
		//クッキーからユーザー情報の取得
		if (managerCookie[i].getName().equals("manager_id")) {
	managerid = managerCookie[i].getValue();
		}
		//クッキーからパスワード情報の取得
		if (managerCookie[i].getName().equals("manager_password")) {
	managerpassword = managerCookie[i].getValue();
		}
	}
}
//エラーメッセージがない(null)場合の代入処理
if (error != null) {
	error = "";
}
%>


<html>
<head>
<title>管理者用ログイン画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>



	<p style="text-align: center; font-size: 24px"></p>
	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 350px">
		<form action="<%=request.getContextPath()%>/managerLogin"
			method="post">
			<class="detail-table">
			<p class="error-msg"><%=error%></p>
			<tr>
				<th>ユーザー</th>
				<td><input type=text size="30px" name="managerid"
					value=<%=managerid%>></input></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" size="30px" name="managerpassword"
					value=<%=managerpassword%>></input></td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: center"><input type="submit"
					value="ログイン"></td>
			</tr>
			</table>
		</form>
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>