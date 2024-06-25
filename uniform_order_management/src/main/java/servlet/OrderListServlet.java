/**
 *受注一覧機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.OrderItem;
import dao.orderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");
			

			//OrderedItemDAOオブジェクト宣言
			orderItemDAO orderItemDao = new orderItemDAO();

			//OrderedItemのリスト取得
			ArrayList<OrderItem> OrderItemList = orderItemDao.selectAll();
			
			

			//セッションに格納
			request.setAttribute("order_list", OrderItemList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入状況確認は出来ません。";
			cmd = "menu";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "menu";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合OrderDetailServletにフォワード
				request.getRequestDispatcher("/orderDetail").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}