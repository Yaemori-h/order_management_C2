package servlet;

import java.io.IOException;

import bean.OrderItem;
import dao.orderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.SendMail;

@WebServlet("/updatePayAndShip")
public class UpdatePayAndShipServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			//入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			//パラメータの取得
			String orderIdStr = request.getParameter("order_id");
			int orderId = Integer.parseInt(orderIdStr);
			String depositStatus = request.getParameter("deposit_status");
			String sendingStatus = request.getParameter("sending_status");

			//受注情報を更新 updateメソッド
			orderItemDAO orderItemDao = new orderItemDAO();
			OrderItem orderItem = orderItemDao.selectByOrderId(orderId);
			if (orderItem != null) {
				orderItem.setDepositstatus(depositStatus);
				orderItem.setSendingstatus(sendingStatus);
				orderItemDao.update(orderItem);
			} else {
				error = "指定された受注情報が存在しません。";
				cmd = "list";
			}
			// メール送信処理
			if ("1".equals(depositStatus)) {
				String address = orderItem.getUser().getEmail();
				String emailTitle = "神田ユニフォーム|ご入金を確認いたしました";
				String emailContent = "いつもご利用いただきありがとうございます。\n\n"
						+ "先日のご注文について、ご入金を確認いたしました。\n\n"
						+ "これから商品の発送のお手続きに入らせていただきます。\n\n"
						+ "商品発送後、再度ご連絡いたします。\n"
						+ "引き続き何卒よろしくお願い申し上げます。\n\n"
						+ "--------------------------------------------------------\n"
						+ "神田ユニフォーム\n"
						+ "--------------------------------------------------------";
				SendMail.send(address, emailTitle, emailContent);
			}

			if ("1".equals(sendingStatus)) {
				String address = orderItem.getUser().getEmail();
				String emailTitle = "神田ユニフォーム|商品を発送いたしました";
				String emailContent = "いつもご利用いただきありがとうございます。\n\n"
						+ "先日のご注文について、商品を発送いたしましたのでご連絡いたします。\n\n"
						+ "到着までもうしばらくお待ちください\n\n"
						+ "引き続き何卒よろしくお願い申し上げます。\n\n"
						+ "--------------------------------------------------------\n"
						+ "神田ユニフォーム\n"
						+ "--------------------------------------------------------";
				SendMail.send(address, emailTitle, emailContent);
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーのため、受注情報を更新できませんでした。";
			cmd = "menu";
		} catch (NumberFormatException e) {
			error = "受注IDが不正です。";
			cmd = "orderList";
		} finally {
			if (error.equals("")) {
				response.sendRedirect(request.getContextPath() + "/orderList");
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/managerError.jsp").forward(request, response);
			}
		}
	}
}