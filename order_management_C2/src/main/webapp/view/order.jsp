<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.Item,java.util.*, dao.ItemDAO"%>
<%
ItemDAO itemDao=new ItemDAO();
Item item =new Item();
ArrayList<Item> itemList=new ArrayList<Item>();

int i=1;
while(true){
	item=itemDao.selectByItem_id(i);
	
	if(item.getItem_name()==null){
		break;
	}
	
	itemList.add(item);
	i++;
}
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
	<%@ include file="/common/buyerHeader.jsp"%>

	<!-- タイトル -->
	<div id="main">
		<div>
			<h2>注文フォーム</h2>
		</div>
		
		<form action="<%=request.getContextPath()%>/order">
			<table>
				<tr>
					<th>商品</th>
					<td><select name="selectItem" id="item" onchange="stock()">
					<option value="" disabled selected>選択してください</option>
					<% for (int j=0;j<itemList.size();j++){ %>
						
						<option value=<%=itemList.get(j).getItem_id()%>><%=itemList.get(j).getItem_name()%></option>
					<% } %>
					</select></td>
				</tr>
				
				<tr>
					<th>個数</th>
					<td><select name="selectNum" id="quantity">
					<option value="" disabled selected>選択してください</option>
					
					<% for (int j=0;j<itemList.size();j++){ 
						for(int k=0;k<itemList.get(j).getStock();k++){%>
							
							<option value="<%=k+1%>"><%=k+1%></option>
							
						<% } %>
					<% } %>
					</select></td>
				</tr>	
				
				<tr>
					<th>備考欄(200文字以内)</th>
					<td><textarea name="note" rows="3" cols="20"></textarea></td>
				</tr>
	
			</table>
			
			<input type="submit" value="購入">
		
		</form>
		
		<script type="text/javascript">
			function stock(){
				var item = document.getElementById("item");
				var quantity = document.getElementById("quantity");
			
				for (var a=0;a<quantity.length;a++){
					quantity.options[a].disabled=true;
				}
	
				var  y=0;
				<%for(int x=0;x<itemList.size();x++){ %>
						
				       <%for(int z=0;z<itemList.get(x).getStock();z++){%>
				       		 y++; 
				       		if(item.value==<%=itemList.get(x).getItem_id()%>){
								quantity.options[y].disabled =false;
				       		}
						
					<%}%>
				<%}%>
	
			}
		</script>


	</div>
	<!-- フッター -->
	<%@ include file="/common/buyerFooter.jsp"%>
</body>

</html>