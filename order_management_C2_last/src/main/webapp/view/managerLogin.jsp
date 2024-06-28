<!--
管理者用ログイン

作成日：2024/6/21
作成者：南部優実
-->
<%@page contentType="text/html; charset=UTF-8"%>

<%
String managerid = ""; //ユーザーを管理する変数
String managerpassword = ""; //パスワードを管理する変数

String error = (String) request.getAttribute("error"); //エラーメッセージの取得

%>


<html>
<head>
<title>管理者用ログイン画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/managerHeader.jsp"%>
	
	
	<div id="main">
		<!-- タイトル -->
		<div>
			<h2>ログイン</h2>
		</div>
	
		<form action="<%=request.getContextPath()%>/managerLogin"
			method="post">
			<table>
			<tr>
				<td>ユーザー</td>
				<td><input type=text size="30px" name="managerid"
					value=<%=managerid%>></input></td>
			</tr>
			<tr>
				<td>パスワード</td>
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
	<%@ include file="/common/managerFooter.jsp"%>
</body>
</html>