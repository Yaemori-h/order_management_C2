<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>

<head>
	<meta charset="UTF-8">
	<title>注文フォーム</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>
	<!-- ブラウザ全体 -->

	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<!-- タイトル -->
	<div>
		<h2>注文フォーム</h2>
	</div>

	<!-- コンテンツ  -->
	<div class="content">
		<form action="#" method="post">

			<div class="form-group">
				<label for="product">商品種類</label>
				<input type="text" id="product" name="product" required>
			</div>

			<div class="form-group">
				<label for="quantity">購入個数</label>
				<input type="number" id="quantity" name="quantity" required>
			</div>

			<div class="form-group">
				<label for="remarks">備考欄</label>
				<input type="text" id="remarks" name="remarks" required>
			</div>
		</form>

		<div class="form-group">
			<button type="submit">送信</button>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

</body>

</html>