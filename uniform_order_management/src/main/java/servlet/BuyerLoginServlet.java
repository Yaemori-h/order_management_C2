/**
 * BuyerLoginServlet 
 * 
 * 作成日：2024/6/21
/* 作成者：尹昭喜
*/

package servlet;

import java.io.IOException;

import bean.User;
import dao.userDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class BuyerLoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			//③画面からの入力情報を受け取るためのエンコードを設定します。
			request.setCharacterEncoding("UTF-8");

			//パラメータの取得
			String userid = (String) request.getParameter("userid");
			String password = (String) request.getParameter("password");
	
			
			//UserDAOをインスタンス化し、関連メソッドを呼び出す。
			userDAO objDao = new userDAO();
			User user = objDao.selectByUser(userid,password);
			

			//User情報check
			if (user.getEmail() == null) {
				error = "入力データが間違っています。";
				cmd = "login";
				return;
			}
			//User情報がある場合
			if (userid.equals(user.getUserid()) && password.equals(user.getPassword())) {

				HttpSession session = request.getSession();
				// 取得したUserオブジェクトをセッションスコiiープに"user"という名前で登録する。
				session.setAttribute("user", user);
				//クッキーに入力情報のuseridとpasswordを登録する。（期間は5日間）
				Cookie cookie = new Cookie("userid", userid);
				cookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookie);

				Cookie cookie1 = new Cookie("password", password);
				cookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookie1);

			}
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログイン出来ません。";
			cmd = "login";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はitemList.jspにフォワード
				request.getRequestDispatcher("/view/itemList.jsp").forward(request, response);

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