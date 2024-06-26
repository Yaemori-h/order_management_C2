/**
 * 管理者ログイン機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 */
package servlet;

import java.io.IOException;

import bean.Manager;
import bean.User;
import dao.managerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/managerLogin")
public class ManagerLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";

		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");
			
			//パラメータの取得
			String managerid = request.getParameter("managerid");
			String managerpassword = request.getParameter("managerpassword");

			//UserDAOの呼び出し
			managerDAO managerDao = new managerDAO();

			//Userから1件もらう
			Manager Manager = managerDao.selectByManager(managerid, managerpassword);
			
			//ログイン処理
			if (!managerid.equals(User.getManager_id()) || !managerpassword.equals(User.getManager_password())) {
				error = "入力データが間違っています。";
				cmd = "login";
				return;
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("manager", Manager);

				//IDのクッキー
				Cookie idCookie = new Cookie("managerid", managerid);
				idCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(idCookie);

				//passwordのクッキー
				Cookie passwordCookie = new Cookie("managerpassword", managerpassword);
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(passwordCookie);
			}


		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "login";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "login";

		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				if (cmd.equals("login")) {
					request.getRequestDispatcher("/view/login.jsp").forward(request, response);
				} else {
					request.setAttribute("cmd", cmd);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}
		}
	}
}