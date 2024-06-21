<!--   
管理者用メニューjsp
  
  作成日：2024/6/21
  作成者：南部優実
  -->
  <%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>神田ユニフォーム</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->

	<!-- ヘッダー部分 -->
	<%@ include file="/common/managerHeader.jsp"%>
	<!-- メニュー部分 -->
	<div>
		<div>
			<!-- ページタイトル -->
			<div>
				<h2>メニュ</h2>
			</div>
		</div>
	</div>

	<!-- コンテンツ(本文) -->
	<div>
		<table>
			<tr>
				<td><a href="<%=request.getContextPath()%>/orderList">【受注状況一覧】</a></td>
			</tr>
			<tr>
				<td><a href="<%=request.getContextPath()%>/logout">【ログアウト】</a></td>
			</tr>
		</table>
	</div>

	<!-- フッター部分 -->
	<%@ include file="/common/managerFooter.jsp"%>

</body>
</html>