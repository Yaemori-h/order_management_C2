<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>ユニフォーム受注管理システム</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		<!-- ブラウザ全体 -->
		
			<!-- ヘッダー部分 -->
			<%@ include file="/common/header.jsp"%>
			<!-- メニュー部分 -->
			<div>
				<div>
					<!-- ページタイトル -->
					<div>
						<h2>MENU</h2>
					</div>
				</div>
			</div>
			
			<!-- コンテンツ(本文) -->
			<div>
			<a href="<%=request.getContextPath()%>/orderList">【受注状況一覧】</a>
			</div>
			
			<div>
			<a href="<%=request.getContextPath()%>/logout">【ログアウト】</a>
			</div>
			
			<!-- フッター部分 -->	
			<%@ include file="/common/footer.jsp"%>
		
	</body>
</html>