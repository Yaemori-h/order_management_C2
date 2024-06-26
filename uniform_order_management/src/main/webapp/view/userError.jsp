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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/buyerHeader.jsp"%>
	
	<div id="main">
		<h2>●●エラー●●</h2>
	
		<h3><%=error %></h3>
	
		<p>
		<%if (cmd.equals("menu")) {%>
			<a href="<%=request.getContextPath()%>/view/menu.jsp">【メニュー画面へ】</a>
		<%
		}else if(cmd.equals("")){
		%>
			<a href="<%=request.getContextPath()%>/view/buyerLogin.jsp">【ログイン画面へ】</a>
		<% 
		//すべての機能実装終わったらここを埋めます>
		}
		%>
		
		</p>
		
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/buyerFooter.jsp"%>
</body>

</html>