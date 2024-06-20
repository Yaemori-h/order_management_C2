<%@page contentType="text/html; charset=UTF-8"%>



 
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	
	
	
	<p style="text-align: center; font-size: 24px"></p>
	<hr style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 350px">
		<form action="<%=request.getContextPath()%>/login"  method="post">
			<class="detail-table">
				
				<tr>
					<th
						style="background-color: #6666ff; width: 30px; horizontal-tb: vertical-rl">ユーザー</th>
					<td><input type=text size="30px" name="user" value=""></input></td>
				</tr>
				<tr>
					<th
						style="background-color: #6666ff; width: 30px; horizontal-tb: vertical-rl">パスワード</th>
					<td><input type="password" size="30px" name="password" value=""></input></td>
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