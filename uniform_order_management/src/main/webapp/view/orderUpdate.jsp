<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//detailservletでorderItemをリクエストスコープで設定しここで受け取る。
//受け取ったorderItemをもとに他の情報も取得する。

OrderItem orderItem =(OrderItem) request.getAttribute("orderInfo");

userDAO userDao=new userDAO();
User user = userDao.selectByUser(orderItem.getUserid());

purchaseInfoDAO purchaseDao = new purchaseInfoDAO();
purchaseInfo purchaseInfo= purchaseDao.selectById(orderItem.getBuyinfoid());//※※※※※※DAOにメソッド追加必要有

itemDAO itemDao=new itemDAO();
Item item =itemDao.selectByItem_id(orderItem.getItemid());





%>

<html>

<head>
	<meta charset="UTF-8">
	<title>受注更新</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<!-- header -->
<%@ include file="/common/managerHeader.jsp"%>
<div id="main">
	<div>
		<h2>受注更新</h2>
	</div>
	
	
	<div>
		<form action="<%=request.getContextPath()%>/payAndShip"  method="get">
		<table>
	
			
			<!-- 更新テーブル -->
			<tr>
				<th>No</th>
				<th>氏名</th>
				<th>種類</th>
				<th>個数</th>
				<th>合計金額</th>
				<th>発注日</th>
			</tr>
				
			<tr>
				<td><%=orderItem.getOrderid()%><input type="hidden" name="id" value=<%=orderItem.getOrderid()%>></td>
				<td><%=user.getName()%></td>
				<td><%=item.getItem_name() %></td>
				<td><%=purchaseInfo.getQuantity() %></td>
				<td><%=purchaseInfo.getTotal()%></td>
				<td><%=purchaseInfo.getOrderDate() %></td>
			</tr>
		</table>
		
		
			<table>		
				<tr>
					<th>入金金額</th>
					<th>発送状況</th>
				</tr>
				<!-- 更新後 -->
				<tr>
					<td><select name="deposit">
						<option value="1">1.未入金</option>
						<option value="2">2.入金済</option>
					</select></td>
					<td><select name="sending">
						<option value="1">1.未発送</option>
						<option value="2">2.発送済</option> 
					</select></td>
				
				</tr>
			</table>
		
			
		</div>
		<!-- 更新完了	ボタン -->
		<div>
			<input type="submit" value="更新完了">
		</div>
		</form>
	</div>

</div>


<!-- footer -->
<%@ include file="/common/managerFooter.jsp"%> 

</body>
</html>