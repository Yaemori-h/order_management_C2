<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>

<head>
	<meta charset="UTF-8">
	<title>購入確認</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>
	

	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

	<!-- main -->
	<div id="main">
		<!-- タイトル -->
		<div>
			<h2>購入確認画面</h2>
		</div>
	
		<!-- コンテンツ  -->
		<div class="content">
			<p>下記の商品を購入しました。<br>ご利用ありがとうございました。</p>
		</div>
	
		<table>
			<tr>
				<th>商品の種類</th>
				<th>購入個数</th>
			</tr>
			<!--購入した商品情報を表示-->
	
		</table>
	
		<div class="total">
			<p>合計金額:円</p>
		</div>
	</div>

	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>

</body>

</html>