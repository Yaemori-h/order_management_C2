/* 作成者：八重森
 * 作成日：6/24
 * 
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

@WebServlet("/payAndShip")
public class PayAndShipServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			//更新後のパラメータを取得
			String pay =request.getParameter("deposit");
			String ship =request.getParameter("sending");
			String strOrderId =request.getParameter("id");
			int orderId=Integer.parseInt(strOrderId);
			
			
			OrderItem order=new OrderItem();//更新後の情報を格納するオブジェクト
			orderItemDAO orderDao=new orderItemDAO();
			
			order.setDepositstatus(pay);
			order.setSendingstatus(ship);
			order.setOrderid(orderId);
			
			//orderItemDAOのupdateメソッドを呼び出し更新する。
			orderDao.update(order);
			
		} catch (IllegalStateException e) {
			error = e+"DB接続エラーの為、更新処理は行えませんでした";
			cmd = "logout";//※※※※※※DBエラーのときいきたいコマンドを書く。
		} finally {
			if (error.equals("")) {//エラーがなければListServletへ
				request.getRequestDispatcher("/orderList").forward(request, response);
			} else {//エラーがあればmanagerError.jspへ
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/managerError.jsp").forward(request, response);
				
			}
		}
	}
}
