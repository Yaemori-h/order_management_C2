<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String error = (String) request.getAttribute("error");
if (error == null) {
	error = "";
}
String cmd = (String) request.getAttribute("cmd");
if (cmd == null) {
	cmd = "";
}

%>

<html>

<head>
<meta charset="UTF-8">
<title>エラー</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/buyerHeader.jsp"%>

	<div id="main">
		<h2>●●エラー●●</h2>

		<h3><%=error%></h3>

		<p>
			<%if (cmd.equals("login")) {%>
			<a href="<%=request.getContextPath()%>/view/buyerLogin.jsp">【ログイン画面へ】</a>
			<%
			} else if (cmd.equals("insert")) {
			%>
			<a href="<%=request.getContextPath()%>/view/insert.jsp">【初回登録画面へ】</a>

			<%
			} else if (cmd.equals("order")) {
			%>
			<a href="<%=request.getContextPath()%>/view/order.jsp">【注文フォーム画面へ】</a>

			<%
			} else if (cmd.equals("orderConfirm")) {
			%>
			<a href="<%=request.getContextPath()%>/view/orderConfirm.jsp">【注文確認画面へ】</a>
			<%
			}
			%>

		</p>

	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/buyerFooter.jsp"%>
</body>

</html>