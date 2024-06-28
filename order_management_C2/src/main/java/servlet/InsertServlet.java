/**
 /**
 * 新規会員登録機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
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

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			//入力データの取得
			String inputMail = request.getParameter("mail");
			String inputPassword = request.getParameter("password");
			String inputName = request.getParameter("name");
			String inputAddress = request.getParameter("address");

			//未入力チェック
			if (inputMail.equals("")) {
				error = "メールアドレスが未入力の為、会員登録が行えませんでした。";
				cmd = "insert";
				return;
			}

			if (inputPassword.equals("")) {
				error = "パスワードが未入力の為、会員登録が行えませんでした。";
				cmd = "insert";
				return;
			}

			if (inputName.equals("")) {
				error = "名前が未入力の為、会員登録が行えませんでした。";
				cmd = "insert";
				return;
			}

			if (inputAddress.equals("")) {
				error = "住所が未入力の為、会員登録が行えませんでした。";
				cmd = "insert";
				return;
			}

			//userDAOオブジェクト生成
			userDAO userDao = new userDAO();

			// メールアドレスの重複チェック
			String chkUser = userDao.selectByUser(inputMail).getEmail();
			if (chkUser != null) {
				error = "メールアドレスは既に登録済みの為、会員登録処理は行えませんでした。";
				cmd = "insert";
				return;
			}

			//isbn、title、priceを設定   
			User User = new User();
			User.setEmail(inputMail);
			User.setPassword(inputPassword);
			User.setName(inputName);
			User.setAddress(inputAddress);

			// DBに登録
			userDao.insert(User);
			
			//下3行追加
			
			User newUser = userDao.selectByUser(inputMail, inputPassword);
			
			
			HttpSession session = request.getSession();
			
			session.setAttribute("user", newUser);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、会員登録処理ができませんでした。";
			cmd = "insert";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "insert";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はorderServletにフォワード		　　
				request.getRequestDispatcher("/view/order.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/userError.jsp").forward(request, response);
			}
		}
	}

}