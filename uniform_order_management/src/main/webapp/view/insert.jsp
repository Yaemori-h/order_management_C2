<!--
  新規会員登録jsp
  
  作成日：2024/6/21
  作成者：南部優実
 -->
<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>初回登録フォーム</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		
		
		<!-- ヘッダー部分 -->
		<%@ include file="/common/buyerHeader.jsp" %>
		
		<!-- コンテンツ(本文) -->
		<h2>初回登録</h2>
		<div id="main">
			<form action="<%=request.getContextPath()%>/insert" method="post">
				<table>
				<tr>
					<td>メールアドレス</td>
					<td><input type="text" name="mail"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>氏名</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>住所</td>
					<td><input type="text" name="address"></td>
				</tr>
				
				</table>
				<div>
					<input type="submit" value="送信">
				</div>
			</form>
		</div>
		
		<!-- フッター部分 -->	
		<%@ include file="/common/buyerFooter.jsp" %>
		
	</body>
</html>