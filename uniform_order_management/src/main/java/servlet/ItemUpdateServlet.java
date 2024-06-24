/**
 *管理者用商品詳細表示機能
 * 
 * 作成日：2024/6/24
 * 作成者：南部優実
 */

package servlet;

import java.io.IOException;

import bean.Item;
import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/itemAddDetail")
public class ItemUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			//パラメータの取得
			String item_id = request.getParameter("item_id");
			String item_name = request.getParameter("item_name");
			String strPrice = request.getParameter("price");
			String strStock = request.getParameter("stock");

			if (item_name.equals("")) {
				error = "商品名が未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}
			if (strPrice.equals("")) {
				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}
			if (strStock.equals("")) {
				error = "在庫数が未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			int price;
			int stock;
			try {
				// 値チェック（整数かどうか）
				price = Integer.parseInt(strPrice);
				price = Integer.parseInt(strStock);
			} catch (NumberFormatException e) {
				error = "値が不正の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// ItemDAOオブジェクト生成
			ItemDAO ItemDao = new ItemDAO();

			// 入力されたISBNの存在チェック
			if (ItemDao.selectByItem_id(item_id).geItem_id() == null) {
				error = "更新対象が存在しない為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			//各種を設定
			Item Item = new Item();
			Item.setItem_id(item_id);
			Item.setItem_name(item_name);
			Item.setPrice(price);
			Item.setPrice(stock);

			// 更新メソッドを呼び出す。
			ItemDao.update(Item);

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。" + e;
			cmd = "logout";

		} finally {
			if (cmd.equals("")) {
				// 表示対象が無い場合はitemAddDetail.jspにフォワード
				request.getRequestDispatcher("/view/itemAddDetail.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}