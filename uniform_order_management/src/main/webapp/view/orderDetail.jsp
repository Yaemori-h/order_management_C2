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
					<!-- ナビゲーション  -->
					<div id="nav">
						<ul>
							<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
						</ul>
					</div>
					<!-- ページタイトル -->
					<div>
						<h2>受注詳細</h2>
					</div>
				</div>
			</div>
			
			<!-- コンテンツ(本文) -->
			<table>
				<thead>
					<tr>
						<th>メールアドレス</th>
						<th>氏名</th>
						<th>住所</th>
						<th>商品の種類</th>
						<th>購入個数</th>
						<th>備考欄</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			
			
			<!-- フッター部分 -->	
			<%@ include file="/common/footer.jsp"%> 
		
	</body>
</html>