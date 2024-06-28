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
			// エンコードを設定します。
			request.setCharacterEncoding("UTF-8");

			// パラメータの取得
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");

			// userDAOをインスタンス化し、関連メソッドを呼び出す。
			userDAO userDao = new userDAO();
			User user = userDao.selectByUser(mail, password);

			// User情報チェック
			if (user.getEmail() == null) {
				error = "入力データが間違っています。";
				cmd = "login";
			}
			HttpSession session = request.getSession();
			// 取得したUserオブジェクトをセッションスコープに"user"という名前で登録する。
			session.setAttribute("user", user);
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログイン出来ません。";
			cmd="login";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はフォワード
				request.getRequestDispatcher("/view/order.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				//				if (cmd.equals("login")) {
//					request.getRequestDispatcher("/view/userErr.jsp").forward(request, response);

//				} else {
					request.getRequestDispatcher("/view/userError.jsp").forward(request, response);
				//}
			}
		}
	}
}