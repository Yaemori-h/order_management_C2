package servlet;

import java.io.IOException;

import bean.Manager;
import dao.managerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/managerLogin")
public class ManagerLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			// 文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			// パラメータの取得
			String managerId = request.getParameter("managerid");
			String managerPassword = request.getParameter("managerpassword");

			// managerDAOの呼び出し
			managerDAO managerDao = new managerDAO();

			// Managerを取得
			Manager manager = managerDao.selectById(managerId);

			// ログイン処理
			if (manager == null || !managerPassword.equals(manager.getManagerPassword())) {
				error = "入力データが間違っています。";
				cmd = "login";
			} else {
				// ログイン成功時の処理
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
				
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "login";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "login";

		}
		if(error != "") {
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/managerError.jsp").forward(request, response);
		}
		
	}
}