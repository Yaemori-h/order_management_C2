<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.OrderItem"%>
<%
ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>) request.getAttribute("order_list");
int thisMonthSales = (int) request.getAttribute("this_month_sales");
int lastMonthSales = (int) request.getAttribute("last_month_sales");
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
		<%@ include file="/common/managerHeader.jsp"%>
		
		<!-- メニュー部分 -->
	
    
            <!-- ナビゲーション  -->
            <div id="nav">
                <ul>
                    <li><a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a></li>
                </ul>
            </div>
            <!-- ページタイトル -->
            <div id="main">
            <div>
                <h2>受注一覧</h2>
            </div>
       

		<!-- 2か月分の売上 -->
		<div>
            <p>今月の売り上げ：<%= thisMonthSales %>円</p>
            <p>前月の売り上げ：<%= lastMonthSales %>円</p>
        </div>


		<!-- 受注リスト-->
		<div id="main" class="container">
			<table class="list-table">
				<thead>
					<tr class="m-table">
						<th>No.</th>
						<th>氏名</th>
						<th>種類</th>
						<th>個数</th>
						<th>合計金額</th>
						<th>発注日</th>
						<th>入金状況</th>
						<th>発注状況</th>
						<th>詳細</th>
						<th>更新</th>
					</tr>
				</thead>
				<tbody>
					<%
                    if (orderItemList != null) {
                        for (int i = 0; i < orderItemList.size(); i++) {
                            OrderItem orderItem = orderItemList.get(i);
                    %>
					<tr>
						<td><%= orderItem.getOrderid() %></td>
						<td><%= orderItem.getUser().getName() %></td>
						<td><%= orderItem.getItem().getItem_name() %></td>
						<td><%= orderItem.getQuantity() %></td>
						<td><%= orderItem.getTotal() %></td>
						<td><%= orderItem.getOrderDate() %></td>
						<td><%= orderItem.getDepositstatus().equals("1") ? "入金済み" : "未入金" %></td>
						<td><%= orderItem.getSendingstatus().equals("1") ? "発送済み" : "未発送" %></td>
						<td><a href ="<%=request.getContextPath()%>/orderDetail?order_id=<%=orderItem.getOrderid()%>&cmd=detail ">詳細</a></td>
						<td><a href="<%=request.getContextPath()%>//payAndShip?order_id=<%=orderItem.getOrderid()%>&cmd=update">更新</a></td>
					</tr>
					<%
                        }
                    }
                    %>
				</tbody>
			</table>
		</div>
		</div>
		<!-- footer -->
		<%@ include file="/common/managerFooter.jsp"%>
	</div>
</body>
</html>
