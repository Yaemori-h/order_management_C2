<%@page contentType="text/html; charset=UTF-8"%>

<%
// ユーザーを管理する変数
String userid = "";
// パスワードを管理する変数
String password = "";
// メールアドレスを管理する変数
String mail = (String) request.getAttribute("mail");

// クッキーの取得
Cookie[] userCookie = request.getCookies();
// エラーメッセージの取得
String error = (String) request.getAttribute("error");

if (userCookie != null) {
	for (int i = 0; i < userCookie.length; i++) {
		// クッキーからユーザー情報の取得
		if (userCookie[i].getName().equals("userid")) {
			userid = userCookie[i].getValue();
		}
		// クッキーからパスワード情報の取得
		if (userCookie[i].getName().equals("password")) {
			password = userCookie[i].getValue();
		}
	}
}
// エラーメッセージがない(null)場合の代入処理
if (error == null) {
	error = "";
}
%>

<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<!-- ヘッダー部分 -->
	<%@ include file="/common/buyerHeader.jsp"%>

	<!-- main -->
	<div id="main">

		<!-- タイトル -->
		<div>
			<h2>ログイン</h2>
		</div>
		<!-- 入力フォーム -->
		<form action="<%=request.getContextPath()%>/login" method="post">
			<table>
				<tr>
					<td>ユーザー(メールアドレス)</td>
					<td><input type="text" name="user"></td>
				</tr>

				<tr>
					<td>パスワード</td>
					<td><input type="text" name="password"></td>
				</tr>

			</table>
			<input type="submit" value="ログイン">
		</form>
		<!-- 初回・管理者への案内 -->
		<div class="login-induction">
			<ul>
				<li><a href="<%=request.getContextPath()%>/view/insert.jsp">初回の方はこちら</a></li>
				<li><a href="<%=request.getContextPath()%>/view/managerLogin.jsp">管理者の方はこちら</a></li>
			</ul>
		</div>
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/buyerFooter.jsp"%>

</body>
</html>