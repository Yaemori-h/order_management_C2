/**
 * 受注詳細確認機能
 * 
 * 作成日：2024/6/21
 * 作成者：尹昭喜
 */


package servlet;

import java.io.IOException;

import bean.OrderItem;
import dao.orderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			String order_id = (String) request.getParameter("order_id");

			orderItemDAO orderItemDao = new orderItemDAO();
			OrderItem OrderItem = orderItemDao.selectByOrderId(Integer.parseInt(order_id));

			//String strOrderId=Integer.toString(OrderItem.getOrderid());
			if (OrderItem == null || OrderItem.getOrderid() == 0) {

				
					error = "更新対象の情報が存在しない為、更新画面は表示できませんでした。";
					cmd = "orderList";
					return;
				

			}

			request.setAttribute("OrderItem", OrderItem);

		} catch (IllegalStateException e) {
			
				error = "DB接続エラーの為、変更画面は表示できませんでした。";
				cmd = "orderList";
			
		} finally {
			if (error.equals("")) {
				
					request.getRequestDispatcher("/view/orderDetail.jsp").forward(request, response);
				
			} else {//エラーメッセージがあるならば
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/managerError.jsp").forward(request, response);
			}
		}

	}
}