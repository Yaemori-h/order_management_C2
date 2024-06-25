/**
 * 受注詳細確認機能
 * 
 * 作成日：2024/6/21
/* 作成者：尹昭喜
*/

package servlet;

import java.io.IOException;

import bean.purchaseInfo;
import dao.orderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";
		
		try {
			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");
			
			orderItemDAO daoobj = new orderItemDAO();
			String order_id = (String) request.getParameter("order_id");
			purchaseInfo purchaseinfo = daoobj.selectBybuyInfo_Id(order_id);
			

			if (purchaseinfo.getbuyinfo_id() == 0) {
				
				if (cmd.equals("update")) {
					error = "更新対象の情報が存在しない為、更新画面は表示できませんでした。";
					cmd = "list";
					return;
			}
				
			}
				
				request.setAttribute("purchaseinfo", purchaseinfo);
				
			} catch (IllegalStateException e) {
				if (cmd.equals("update")) {
					error = "DB接続エラーの為、変更画面は表示できませんでした。";
					cmd = "logout";
				}
			} finally {
				if (error.equals("")) {
					if (cmd.equals("detail")) {
						request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("/view/update.jsp").forward(request, response);
					}
				} else {//エラーメッセージがあるならば
					request.setAttribute("cmd", cmd);
					request.setAttribute("error", error);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}

		}
}