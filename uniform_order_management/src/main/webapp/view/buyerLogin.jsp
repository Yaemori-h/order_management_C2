<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		
		
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp" %>
			
		<!-- main -->
		<div id="main">
		
			<!-- タイトル -->
			<div>
				<h2>ログイン</h2>
			</div>
			<!--  入力フォーム -->
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
					<li><a href="<%=request.getContextPath()%>/view/iniLogin.jsp">初回の方はこちら</a></li>
					<li><a href="<%=request.getContextPath()%>/view/managerLogin.jsp">管理者の方はこちら</a></li>		
				
				</ul>
			</div>	
		</div>
		<!-- フッター部分 -->	
		<%@ include file="/common/footer.jsp" %>
		
	</body>
</html>