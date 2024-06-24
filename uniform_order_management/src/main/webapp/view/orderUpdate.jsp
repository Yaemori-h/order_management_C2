<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//list.jspからパラメータで受注IDと










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
				<td>No</td>
				<td>氏名</td>
				<td>種類</td>
				<td>個数</td>
				<td>合計金額</td>
				<td>発注日</td>
			</tr>
		</table>
		
		<form action="<%=request.getContextPath()%>/payAndShip"  method="get">
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