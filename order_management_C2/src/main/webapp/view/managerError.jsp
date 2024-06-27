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
	<%@ include file="/common/managerHeader.jsp"%>

	<div id="main">
		<h2>●●エラー●●</h2>
		<h3><%=error%></h3>

		<p>
			<%if (cmd.equals("login")) {%>
			<a href="<%=request.getContextPath()%>/view/managerLogin.jsp">【管理者ログイン画面へ】</a>
			<%
			} else if (cmd.equals("menu")) {
			%>
			<a href="<%=request.getContextPath()%>/view/menu.jsp">【メニュー画面へ】</a>
			<%
			} else if (cmd.equals("orderList")) {
			%>
			<a href="<%=request.getContextPath()%>/view/orderList.jsp">【受注一覧画面へ】</a>
			<%
			} else if (cmd.equals("orderUpdate")) {
			%>
			<a href="<%=request.getContextPath()%>/view/orderUpdate.jsp">【受注更新画面へ】</a>
			<%
			} else if (cmd.equals("itemList")) {
			%>
			<a href="<%=request.getContextPath()%>/view/itemList.jsp">【商品一覧画面へ】</a>
			<%
			} else if (cmd.equals("itemUpdate")) {
			%>
			<a href="<%=request.getContextPath()%>/view/itemUpdate.jsp">【商品更新画面へ】</a>
			<%
			} else if (cmd.equals("itemAdd")) {
			%>
			<a href="<%=request.getContextPath()%>/view/itemAdd.jsp">【商品追加画面へ】</a>
			<%
			}
			%>

		</p>

	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/managerFooter.jsp"%>
</body>

</html>