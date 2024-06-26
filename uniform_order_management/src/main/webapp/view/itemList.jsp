<!-- オプション -->

<!--
  商品一覧機能jsp
  
  作成日：2024/6/24
  作成者：尹昭喜
 -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book,util.MyFormat"%>

<%
ArrayList<Item> item_list = (ArrayList<Item>) request.setAttribute("item_list");
%>

<html>
<head>
<title>ユニフォーム受注管理システム</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div class="wrapper">
		<!--ヘッダー部分  -->
		<%@include file="../common/header.jsp"%>

		<!-- メニュー部分 -->
		<main class="list">
			<div class="h2Border">
				<nav>
					<!-- ナビゲーション  -->
					<ul class="nav">
						<li>[<a href="<%=request.getContextPath()%>/view/itemAdd.jsp">新規商品</a>]
						</li>
					</ul>
				</nav>

				<h2>商品一覧</h2>
			</div>

			<!-- 商品一覧のコンテンツ部分 -->
			<table class="list-table">
				<tr>
					<th>商品No</th>
					<th>商品名</th>
					<th>金額</th>
					<th>在庫数</th>
					<th>更新</th>
				</tr>
				<%
				if (item_list != null) {
					for (int i = 0; i < item_list.size(); i++) {
				%>
				<tr>
					<td><a
						href="<%=request.getContextPath()%>/detail?isbn=<%=item_list.get(i).getIsbn()%>&cmd=detail"><%=item_list.get(i).getIsbn()%></a></td>
					<td><%=item_list.get(i).getTitle()%></td>
					<td><a href="<%=request.getContextPath()%>/detail?isbn=<%=bookList.get(i).getIsbn()%>&cmd=update">更新</a>
					</td>
				</tr>
				<%
				}
				}
				%>
			</table>

		</main>

		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>