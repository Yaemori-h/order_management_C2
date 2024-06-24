
<!--※オプション
 管理者用商品変更確認画面.jsp
 
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
						<li><a
							href="<%=request.getContextPath()%>/view/item.List.jsp">一覧に戻る</a></li>
					</ul>
				</div>

				<!-- ページタイトル -->
				<div id="page_title">
					<h2>商品変更確認</h2>
				</div>
			</div>
		</div>

		<!-- 書籍変更コンテンツ部分 -->
		<
		<div id="main" class="container">
			<table class="list-table">
				<tr>
					<h2>下記が追加されました。 。</h2>
				</tr>
			</table>
			<table class="detail-table">
				<tr>
					<th>商品ID</th>
					<td><%=Item.getItem_id()%></td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><%=Item.getItem_name()%></td>
				</tr>
				<tr>
					<th>価格</th>
					<td><%=format.moneyFormat(book.getPrice())%></td>
				</tr>
				<tr>
					<th>在庫数</th>
					<td><%=Item.getItem_name()%></td>
				</tr>
			</table>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>
</body>
</html>