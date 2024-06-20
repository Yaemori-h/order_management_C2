<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>初回登録フォーム</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		
		
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp" %>
		
		<!-- コンテンツ(本文) -->
		<div id="main">
			<form  method="post">
				<table>
				<tr>
					<td>氏名</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><input type="text" name="mail"></td>
				</tr>
				<tr>
					<td>住所</td>
					<td><input type="text" name="address"></td>
				</tr>
				<tr>
					<input type="submit" value="送信">
				</tr>
				</table>
			</form>
		</div>
		
		<!-- フッター部分 -->	
		<%@ include file="/common/footer.jsp" %>
		
	</body>
</html>