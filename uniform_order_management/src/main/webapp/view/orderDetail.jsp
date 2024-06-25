<!--
  受注詳細確認jsp
  
  作成日：2024/6/24
  作成者：尹昭喜
 -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User,
bean.Item,bean.purchase"%>

<!--リクエストスコープに登録した情報を取得-->
<%
User user = (User) request.getAttribute("user_id");
Purchase purchase = (Purchase) request.getAttribute("purchaseinfo");
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
	<%@ include file="/common/header.jsp"%>

	<!-- メニュー部分 -->
	<div>
		<div>
			<!-- ナビゲーション  -->
			<div id="nav">
				<ul>
					<li><a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a></li>
				</ul>
			</div>
			<!-- ページタイトル -->
			<div>
				<h2>受注詳細</h2>
			</div>
		</div>
	</div>

	<!-- コンテンツ(本文) -->
	<table>
		<thead>
			<tr>
				<th>No</th>
				<td><%=User.getUserid()%></td>
				<th>氏名</th>
				<td><%=User.getName()%></td>
				<th>住所</th>
				<td><%=User.getAddress()%></td>
				<th>メールアドレス</th>
				<td><%=User.getEmail()%></td>

				<th>商品の種類</th>
				<td><%=Item.getItem_name()%></td>

				<th>購入個数</th>
				<td><%=purchase.getquantity()%></td>
				<th>備考欄</th>
				<td><%=purchase.getnote()%></td>

			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>


	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>

</body>
</html>