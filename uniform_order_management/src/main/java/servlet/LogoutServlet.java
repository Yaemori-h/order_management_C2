/**
 * ログアウト機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 */
package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession();
			session.invalidate();

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、登録できませんでした。";
			cmd = "menu";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "menu";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はlogin.jspにフォワード		　　
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}