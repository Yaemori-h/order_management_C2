<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>

<head>
	<meta charset="UTF-8">
	<title>発送状況更新</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<!-- header -->
<%@ include file="/common/header.jsp"%>

<div>
	<h2>発送状況更新</h2>
</div>


<div>

	<table>

		
			<!-- 更新テーブル -->
			<!-- 更新前 -->
			<tr>
				<td>No</td>
				<td>氏名</td>
				<td>種類</td>
				<td>個数</td>
				<td>合計金額</td>
				<td>発注日</td>
				<td>入金金額</td>
				<td>発送状況</td>
			</tr>
			
			<!-- 更新後 -->
			<tr>
			</tr>
	</table>

	
</div>
<!-- 更新完了	ボタン -->
<div>
	<input type="submit" value="更新完了">
</div>


<!-- footer -->
<%@ include file="/common/footer.jsp"%> 

</body>
</html>