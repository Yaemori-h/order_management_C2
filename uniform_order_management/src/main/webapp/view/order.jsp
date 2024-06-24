<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.Item"%>
<%
Item Item =(Item)request.getAttribute("item_list", itemList);
%>

<html>

<head>
<meta charset="UTF-8">
<title>注文フォーム</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>
	<!-- ブラウザ全体 -->

	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

	<!-- タイトル -->
	<div>
		<h2>注文フォーム</h2>
	</div>

	<!-- コンテンツ  -->
	<div class="content">
		<form action="OrderServlet" method="post">
			<div class="form-group">
				商品 <select name="buyinfo_id">
					<option value="1">A</option>
					<option value="2">B</option>
					<option value="3">C</option>
					<option value="4">D</option>
					<option value="5">E</option>
				</select>
			</div>

			<div class="form-group">
				<label for="quantity">個数</label>
				<%=iteminfo.getStock()%>
				<%
					for (int i = 1; i <= iteminfo.getStock(); i++) {
						%><option value=<% i%></option>
				
				<%} %>

				</select><br> <br>

				<div class="form-group">
					<p>
						備考欄
						<textarea name="note" rows="5" cols="40"></textarea>
					</p>
					<br> <br>
				</div>
		</form>

		<div class="form-group">
			<button type="submit">購入</button>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>


	</div>
</body>

</html>