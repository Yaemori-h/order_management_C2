<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.orderItem"%>
<%
ArrayList<OrderItem> OrderItemList = (ArrayList<OrderItem>) request.getAttribute("order_list");
%>
<html>
<head>
<title>受注一覧</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">
		<!-- ヘッダー部分 -->

		<%@ include file="/common/header.jsp"%>

		<!-- 2か月分の売上 -->
<% int last_month =0;
int this_month =0;

						//↓これ下に書いてある（八重森）
					if (OrderItemList != null) {
						for (int i = 0; i < OrderItemList.size(); i++) {
							orderItem orderItem = (orderItem) OrderItemList.get(i).getDate();
							if(orderItem.equal())
								//わかりませーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーん
					%>

		<!-- 受注リスト-->
		<div id="main" class="container">
			<table class="list-table">
				<thead>
					<tr>
						<th>No.</th>
						<th>氏名</th>
						<th>種類</th>
						<th>個数</th>
						<th>合計金額</th>
						<th>発注日</th>
						<th>入金状況</th>
						<th>発注状況</th>
						<th>詳細/更新</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (OrderItemList != null) {
						for (int i = 0; i < OrderItemList.size(); i++) {
							orderItem orderItem = (orderItem) OrderItemList.get(i);
					%>
					<tr>
						<td><%=orderedItem.get(i).getBuyingfo_id()%></td>
						<td><%=orderedItem.get(i).getName()%></td>
						<td><%=orderedItem.get(i).getItem_id()%></td>
						<td><%=orderedItem.get(i).getQuantity()%></td>
						<td><%=orderedItem.get(i).getTotal()%></td>
						<td><%=orderedItem.get(i).getDate()replace("-"),("/")%></td>
						<td><%=orderedItem.get(i).getDeposit_stastus()%></td>
						<td><%=orderedItem.get(i).getSending_status()%></td>
						<td><a href="<%=request.getContextPath()%>/detail">詳細</a> <a
							href="<%=request.getContextPath()%>/update">更新</a>
					</tr>
					<%
						}
					}
						
					%>

				</tbody>
			</table>
		</div>
		<!-- footer -->
		<%@ include file="/common/footer.jsp"%>
	</div>
</body>
</html>
