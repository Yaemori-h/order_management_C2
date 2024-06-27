<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.OrderItem"%>

<%
OrderItem orderItem = (OrderItem)request.getAttribute("orderItem");
%>

<html>
<head>
<meta charset="UTF-8">
<title>受注情報更新</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
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
                <h2>受注情報更新</h2>
            </div>
        

    <!-- コンテンツ(本文) -->
    <div class="container">
        <table class="list-table">
            <thead>
                <tr class="m-table">
                    <th>No</th>
                    <th>氏名</th>
                    <th>種類</th>
                    <th>個数</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= orderItem.getOrderid() %></td>
                    <td><%= orderItem.getUser().getName() %></td>
                    <td><%= orderItem.getItem().getItem_name() %></td>
                    <td><%= orderItem.getQuantity() %></td>
                </tr>
            </tbody>
        </table>
        <br>

        <form action="<%=request.getContextPath()%>/updatePayAndShip" method="post">
            <input type="hidden" name="order_id" value="<%= orderItem.getOrderid() %>">
            <div>
                <label for="deposit_status" class="lightgreen">入金状況</label>
                <select id="deposit_status" name="deposit_status">
                    <option value="0" <%= orderItem.getDepositstatus().equals("0") ? "selected" : "" %>>未入金</option>
                    <option value="1" <%= orderItem.getDepositstatus().equals("1") ? "selected" : "" %>>入金済み</option>
                </select>
            </div>
            <br>
            <div>
                <label for="sending_status" class="lightgreen">発送状況</label>
                <select id="sending_status" name="sending_status">
                    <option value="0" <%= orderItem.getSendingstatus().equals("0") ? "selected" : "" %>>未発送</option>
                    <option value="1" <%= orderItem.getSendingstatus().equals("1") ? "selected" : "" %>>発送済み</option>
                </select>
            </div>
            <div>
                <button type="submit">更新</button>
            </div>
        </form>
    </div>
	</div>
    <!-- フッター部分 -->
    <%@ include file="/common/managerFooter.jsp"%>

</body>
</html>