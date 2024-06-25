/**
 * 購入機能機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Item;
import bean.OrderItem;
import bean.User;
import dao.ItemDAO;
import dao.orderItemDAO;

@WebServlet("/order")
public class OrderServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
			
			//インスタンス化
			ItemDAO ItemDao = new ItemDAO();
			orderItemDAO orderItemDao = new orderItemDAO();
			ArrayList<Item> itemList = new ArrayList<Item>();
			
			//入力パラメータを取得する。
			int buyinfo_id = Integer.parseInt(request.getParameter("buyinfo_id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String note = request.getParameter("note");
			
			//OrderItemをOrderItemとitemListに格納
			OrderItem OrderItem = new OrderItem();
			Item Item = ItemDao.selectByItem_id(OrderItem.getItemid());
			orderItemDao.insert(OrderItem);
			itemList.add(Item);
			
			request.setAttribute("item_list", itemList);
			

		} catch (Exception e) {
			error = "";
			cmd = "menu";
		} finally {
			if (error.isEmpty()) {
				request.getRequestDispatcher("").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
}