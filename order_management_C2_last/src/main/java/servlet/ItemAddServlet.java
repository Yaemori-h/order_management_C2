
//オプション
/**
 * 商品追加用サーブレット
 */
package servlet;

import java.io.IOException;

import bean.Item;
import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/itemAdd")
public class ItemAddServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";
		try {
			//画面からの入力情報を受け取るためのエンコードを設定
			request.setCharacterEncoding("UTF-8");

			//入力パラメータを取得する。
			String item_idStr = request.getParameter("item_id");
			String item_name = request.getParameter("item_name");
			String priceStr = request.getParameter("price");
		    String stockStr = request.getParameter("stock");

			//取得したパラメータの各エラーチェックを行う。
			if (item_idStr.isEmpty()) {
				error = "商品No.が未入力の為、商品追加は行えませんでした。";
				cmd = "menu";
				return;
			} else if (item_name.isEmpty()) {
				error = "商品名が未入力の為、商品追加は行えませんでした。";
				cmd = "menu";
				return;
			} else if (priceStr.isEmpty()) {
				error = "価格が未入力の為、商品追加は行えませんでした。";
				cmd = "menu";
				return;
			} else if (stockStr.isEmpty()) {
				error = "在庫が未入力の為、商品追加は行えませんでした。";
				cmd = "menu";
				return;
			}

			//オブジェクトを生成し、各setterメソッドを利用し、設定する。(エラーが無い場合)
			Item item = new Item();
			item.setItem_id(Integer.parseInt(item_idStr));
			item.setItem_name(item_name);
			item.setPrice(Integer.parseInt(priceStr));
			item.setStock(Integer.parseInt(stockStr));

			//設定したオブジェクトを引数として、DAOをインスタンス化し、関連メソッドを呼び出す。(エラーが無い場合)
			ItemDAO itemDao = new ItemDAO();
			
			//itemDao.selectByItem_id(item_id);

		} catch (Exception e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "menu";

		} finally {
			//⑤エラーの有無でフォワード先を呼び別ける。
			if (error.isEmpty()) {
				request.getRequestDispatcher("/view/itemAdd.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}

}
