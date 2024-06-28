<!-- オプション -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品追加</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->

	<!-- ヘッダー部分 -->
	<%@ include file="/common/managerHeader.jsp"%>

	<!-- ナビゲーション  -->
	<ul class="nav">
		<li>[<a href="<%=request.getContextPath()%>/view/itemList.jsp">一覧に戻る</a>]
		</li>
	</ul>
	</nav>

	<!-- コンテンツ(本文) -->
	<h2>商品追加</h2>
	<div id="main">
		<form action="<%=request.getContextPath()%>/ItemAdd" method="post">
			<table>
				<tr>
					<td>商品No</td>
					<td><input type="text" name="item_id"></td>

				</tr>
				<tr>
					<td>商品名</td>
					<td><input type="text" name="item_name"></td>

				</tr>
				<tr>
					<td>金額</td>
					<td><input type="text" name="price"></td>

				</tr>
				<tr>
					<td>在庫数</td>
					<td><input type="text" name="stock"></td>
				</tr>

			</table>

			<div>
				<input type="submit" value="追加">
			</div>

		</form>
	</div>


	<!-- フッター部分 -->
	<%@ include file="/common/managerFooter.jsp"%>
</body>
</html>