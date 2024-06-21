<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>

<head>
	<meta charset="UTF-8">
	<title>エラー</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	
	<div id="main">
		<h2>●●エラー●●</h2>
	
		<h3>～為、出来ませんでした。</h3>
	
		<p><a href="login.html">【ログイン画面へ】</a></p>
		<p><a href="list.thml">【初期登録画面へ】 </a></p>
		
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>

</body>

</html>