<!--※オプション
 管理者用商品更新.jsp
 
  作成日：2024/6/24
  作成者：南部優実
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Item,"%>

<%
Item Item = (Item) request.getAttribute("item");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細情報</title>
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ナビゲーション  -->
				<div id="nav">
					<ul>
						<li><a href="<%=request.getContextPath()%>/view/item.add.jsp">新規商品登録</a></li>
					</ul>
				</div>

				<!-- ページタイトル -->
				<div id="page_title">
					<h2>商品詳細情報</h2>
				</div>
			</div>
		</div>

		<!-- 書籍変更コンテンツ部分 -->
		<div id="main" class="container">
			<!-- 変更画面 -->
			<form action="<%=request.getContextPath()%>/update">
				<table class="input-table">
					<tbody>
						<tr>
							<th>商品ID</th>
							<td><%=Item.getItem_id()%></td>
							<td><%=Item.getItem_id()%></td>
						</tr>
						<tr>
							<th>商品名</th>
							<td><%=Item.getItem_name()%></td>
							<td><input type="text" name="item_name"></td>
						</tr>
						<tr>
							<th>価格</th>
							<td><%=format.moneyFormat(Item.getPrice())%></td>
							<td><input type="text" name="price"></td>
						</tr>
						<tr>
							<th>在庫数</th>
							<td><%=Item.getItem_name()%></td>
							<td><input type="text" name="stock"></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name=item_id value="<%=Item.getItem_id()%>">
				<input type="submit" value="変更">
			</form>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>
</body>
</html>