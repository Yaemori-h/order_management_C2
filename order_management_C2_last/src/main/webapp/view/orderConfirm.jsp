<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.OrderItem,bean.User,bean.Item,bean.purchaseInfo,jakarta.servlet.http.HttpSession" %>
<%

OrderItem order = (OrderItem) session.getAttribute("order");

User user=order.getUser();
Item item=order.getItem();
purchaseInfo purchaseInfo=order.getPurchaseInfo();

String cmd = (String)request.getAttribute("cmd");

%>

<html>
<head>
<meta charset="UTF-8">
<%if(cmd.equals("order")){ %>
	<title>注文確認画面</title>
<%}else if (cmd.equals("purchase")){ %>	
	<title>購入確認画面</title>
<%} %>	
	
	
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	

		<!-- ヘッダー部分 -->
		<%@ include file="/common/buyerHeader.jsp"%> 


		<!-- カートで購入したもののリスト-->
		<div id="main" >
		<%if(cmd.equals("order")){ %>
			<h2>注文確認</h2>
		<%}else if (cmd.equals("purchase")){ %>	
			<h2>購入確認</h2>
		<%} %>	
		
		<%if(cmd.equals("order")){ %>
			<p>こちらのご注文内容でよろしいでしょうか。</p>
		<%}else if (cmd.equals("purchase")){ %>	
			<p>ご購入ありがとうございます。</p>
			<p>発送までしばらくお待ちください。</p>
		<%} %>
		
		
		<div>
				
		氏名：<%=user.getName()%><br>
		メールアドレス：<%=user.getEmail() %><br>
		住所：<%=user.getAddress() %><br>
		商品種類：<%=item.getItem_name() %><br>
		購入個数：<%=purchaseInfo.getQuantity() %> 個<br>
		合計金額：<%=purchaseInfo.getTotal() %> 円<br>
		備考欄：<%=purchaseInfo.getNote() %><br>
				
				
		</div>	
				
				
				
		<%if(cmd.equals("order")){ %>	
				
		<form action="<%=request.getContextPath()%>/purchase" method="GET">
			<input type="submit" value="確認">
		</form>
		<%}else if (cmd.equals("purchase")){ %>	
			<p><a href="<%=request.getContextPath()%>/view/buyerLogin.jsp">ログイン画面に戻る</p>
		<%} %>

		</div>
		<%@ include file="/common/buyerFooter.jsp"%> 
	
</body>
</html>