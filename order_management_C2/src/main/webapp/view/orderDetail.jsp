<!--
  受注詳細確認jsp
  
  作成日：2024/6/24
  作成者：尹昭喜
 -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.OrderItem,dao.orderItemDAO"%>

<!--リクエストスコープに登録した情報を取得-->
<%
orderItemDAO orderItemDao = new orderItemDAO();
OrderItem orderItem = (OrderItem) request.getAttribute("OrderItem");
%>

<html>
<head>
<meta charset="UTF-8">
<title>ユニフォーム受注管理システム</title>
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
			<!-- ナビゲーション  -->
			<div id="nav">
				<ul>
					<li><a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a></li>
					<li><a href="<%=request.getContextPath()%>/orderList">[受注一覧]</a></li>
				</ul>
			</div>
			<!-- ページタイトル -->
			<div class="title">
				<h2>受注詳細</h2>
			</div>
		</div>
	</div>

	<!-- コンテンツ(本文) -->
	<table>
		<thead class="m-table">
			<tr>
				<th>No</th>
				<td><%=orderItem.getUserid()%></td>
			</tr>
			<tr>
				<th>氏名</th>
				<td><%=orderItem.getUser().getName()%></td>
			</tr>
			<tr>
				<th>住所</th>
				<td><%=orderItem.getUser().getAddress()%>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><%=orderItem.getUser().getEmail()%></td>
			</tr>
			<tr>
				<th>商品の種類</th>
				<td><%=orderItem.getItem().getItem_name()%></td>
			</tr>
			<tr>
				<th>購入個数</th>
				<td><%=orderItem.getQuantity()%></td>
			</tr>
			<tr>
				<th>備考欄</th>
				<td><%=orderItem.getNote()%></td>
			</tr>
		</thead>	
	</table>
	
	<!-- フッター部分 -->
	<%@ include file="/common/managerFooter.jsp"%>

</body>
</html>