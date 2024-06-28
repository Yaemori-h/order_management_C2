//オプション
/**
 * 商品一覧機能
 * 
 * 作成日：2024/6/24
/* 作成者：尹昭喜
*/

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Item;
import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/itemList")
public class ItemListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			request.setCharacterEncoding("UTF-8");
			//ItemDAOをインスタンス化する
			ItemDAO daoObj = new ItemDAO();

			ArrayList<Item> itemList = daoObj.selectAll();

			request.setAttribute("item_list", itemList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示を行えませんでした。";
			cmd = "logout";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はlist.jspにフォワード
				request.getRequestDispatcher("/view/itemList.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
