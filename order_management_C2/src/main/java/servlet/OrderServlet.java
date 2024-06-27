/**
 * 購入機能機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 * 修正：八重森
 */
package servlet;

import java.io.IOException;

import bean.Item;
import bean.OrderItem;
import bean.User;
import bean.purchaseInfo;
import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//変数の宣言
		String error = "";
		String cmd = "";
		
		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");
			
			//セッションオブジェクトの生成
			HttpSession session = request.getSession();

			//セッションから"user"を取得する。
			User user = (User) session.getAttribute("user");

			//セッション切れかどうか
			if (user == null) {
				error = "セッション切れの為、注文出来ません。";
				cmd = "menu";
				return;
			}
			
			String strItemId=request.getParameter("selectItem");//itemid取得
			int itemId=Integer.parseInt(strItemId);
			String strSelectNum=request.getParameter("selectNum");//selectNum取得
			int selectNum=Integer.parseInt(strSelectNum);
			String note=request.getParameter("note");//note取得
			
			
			if(note.length()>200) {
				error="備考欄に200文字以上入力されたため、注文できません。";
				cmd="???";	
				return;
			}
			
			
			purchaseInfo purchaseInfo=new purchaseInfo();
			purchaseInfo.setNote(note);
			purchaseInfo.setQuantity(selectNum);
			
			
			
			OrderItem order=new OrderItem();
			//商品情報を格納
			order.setItemid(itemId);
			//ユーザー情報も格納
		    order.setUser(user);
		    
			//商品の情報を入手
		    ItemDAO itemDao=new ItemDAO();
		    
		    
		    Item item=itemDao.selectByItem_id(itemId);
		    order.setItem(item);
		    int total=item.getPrice()*selectNum;
			//合計金額を格納
		    purchaseInfo.setTotal(total);
		    
		    
		    //購入情報を格納
			order.setPurchaseInfo(purchaseInfo);
		    
			//request.setAttribute("order",order);
			//セッションから送る
		    session.setAttribute("order",order);
		    
			
			
			
			
			
			
			
			
			
			
			
			
//			//インスタンス化
//			ItemDAO ItemDao = new ItemDAO();
//			orderItemDAO orderItemDao = new orderItemDAO();
//			ArrayList<Item> itemList = new ArrayList<Item>();
//			
//			//入力パラメータを取得する。
//			int buyinfo_id = Integer.parseInt(request.getParameter("buyinfo_id"));
//			int quantity = Integer.parseInt(request.getParameter("quantity"));
//			String note = request.getParameter("note");
//			
//			//OrderItemをOrderItemとitemListに格納
//			OrderItem OrderItem = new OrderItem();
//			Item Item = ItemDao.selectByItem_id(OrderItem.getItemid());
//			orderItemDao.insert(OrderItem);
//			itemList.add(Item);
//			
//			request.setAttribute("item_list", itemList);
//			

		} catch (IllegalStateException e) {
			error = "DBエラー";
			cmd = "";
		} finally {
			if (error.isEmpty()) {
				request.setAttribute("cmd","order");
				request.getRequestDispatcher("/view/orderConfirm.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/userError.jsp").forward(request, response);
			}
		}
	}

}

