/**
 * 注文確認機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Item;
import bean.OrderItem;
import bean.User;
import dao.ItemDAO;
import dao.orderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderConfirm")
public class OrderConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";

		try {
			
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession();
			User User = (User) session.getAttribute("user");
			//セッションデーの有無
			if (User == null) {
				error = "セッション切れの為、購入は出来ません。";
				cmd = "logout";
				return;
			}
			
			//ArrayListのOrderセッション取得と有無
			ArrayList<OrderItem> OrderItemList = (ArrayList<OrderItem>) session.getAttribute("order_list");
			if (OrderItemList == null) {
				error = "カートの中に何も無かったので購入は出来ません。";
				cmd = "menu";
				return;
			}
			
			
			//インスタンス化
			ItemDAO ItemDao = new ItemDAO();
			orderItemDAO orderItemDao = new orderItemDAO();

			//bookList生成
			ArrayList<Item> itemList = new ArrayList<Item>();

			//orderListをorderとbookListに格納
			for (int i = 0; i < OrderItemList.size(); i++) {
				OrderItem OrderItem = OrderItemList.get(i);
				Item Item = ItemDao.selectByIsbn(OrderItem.getIsbn());
				orderItemDao.insert(OrderItem);
				itemList.add(Item);
			}
			
			//リクエストスコープでbookListに格納
			request.setAttribute("item_list", itemList);
			
			//情報をクリアにする
			session.setAttribute("order_list", null);
			
			

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入は出来ません。";
			cmd = "logout";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "logout";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はouyConfirm.jspにフォワード		　　
				request.getRequestDispatcher("/view/ouyConfirm.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}