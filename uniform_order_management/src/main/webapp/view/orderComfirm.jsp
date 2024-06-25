<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>注文確認画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">

		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%> 


		<!-- カートで購入したもののリスト-->
		<div id="main" class="container">
			<h2>こちらのご注文内容でよろしいでしょうか。</h2>

			<form action="<%=request.getContextPath()%>/purchase" method="GET">
				<table class="list-table">
					<thead>
						<tr>
							<th>氏名：<%=User.getName()%>"></a>
						</tr>
						<tr>
							<th>メールアドレス：<%=User.getEmail()%>"></a>
						</tr>
						<tr>
							<th>住所：<%=User.getAddress()%>"></a>
						</tr>
						<tr>
							<th>商品情報：<%=User.getValue()%>"></a>
						</tr>
						<tr>
							<th>購入個数：<%=User.getNumber()%>"></a>
						</tr>
						<tr>
							<th>備考欄：<%=User.getCommnt()%>"></a>
						</tr>
					</thead>
					<input type="submit" value="確認">
				</table>
			</form>

		</div>
		<%@ include file="/common/footer.jsp"%> 
	</div>
</body>
</html>