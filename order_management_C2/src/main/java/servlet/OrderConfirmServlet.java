/**
 * 注文確認機能
 * 
 * 作成日：2024/6/21
 * 作成者：南部優実
 */
package servlet;

import java.io.IOException;

import bean.Item;
import bean.OrderItem;
import bean.User;
import bean.purchaseInfo;
import dao.ItemDAO;
import dao.orderItemDAO;
import dao.purchaseInfoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SendMail;

@WebServlet("/purchase")
public class OrderConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";

		try {
				//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			
			OrderItem order = (OrderItem) session.getAttribute("order");
			
			//セッション切れかどうか
			if (order == null) {
				error = "セッション切れの為、注文出来ません。";
				cmd = "??";
				return;
			}
			
			User user=order.getUser();
			Item item=order.getItem();
			purchaseInfo purchaseInfo=order.getPurchaseInfo();
			
			
			 
			
			String mailTitle="ご購入ありがとうございます。";
			String mailContents="この度は当商品をご選択いただき、誠にありがとうございます。\r\n"
								+"下記で注文承りました。\r\n"+"\r\n"
								+"氏名:"+user.getName()+"\r\n"
								+"メールアドレス"+user.getEmail()+"\r\n"
								+"住所"+user.getAddress()+"\r\n"
								+"商品種類"+item.getItem_name()+"\r\n"
								+"購入個数"+purchaseInfo.getQuantity()+"\r\n"
								+"合計金額"+purchaseInfo.getTotal()+"\r\n"
								+"備考欄"+purchaseInfo.getNote()+"\r\n"+"\r\n"
								+"入金が確認でき次第、商品を速やかに発送いたします"+"\r\n"
								+"商品発送後は、再度ご連絡いたします。"+"\r\n"
								+"取引完了まで何卒よろしくお願い申し上げます。"+"\r\n"+"\r\n"
								+"---------------------------------------------------------"+"\r\n"
								+"神田ユニフォーム"+"\r\n"
								+"---------------------------------------------------------"+"\r\n";

			
			SendMail.send(user.getEmail(),mailTitle,mailContents);
			
			
			
			//受注情報をDBに登録するメソッド
			
			purchaseInfoDAO purchaseDao =new purchaseInfoDAO(); 
			orderItemDAO orderDao=new orderItemDAO();
			//////////////////
			int purchaseInfoId=purchaseDao.insert(purchaseInfo);
			
			order.setBuyinfoid(purchaseInfoId);
			
			order.setDepositstatus("0");
			order.setSendingstatus("0");
			
			
			order.setUserid(user.getUserid());	
			orderDao.insert(order);
			
			
			
			
			//在庫を減らすメソッドつまり、在庫を更新するメソッド
			ItemDAO itemDao=new ItemDAO();
			
			//商品IDと現在の在庫を取得
			int itemId=item.getItem_id();
			int beforeStock=item.getStock();
			
			//更新後の在庫を定義
			int afterStock=item.getStock()-purchaseInfo.getQuantity();
			itemDao.update(itemId,afterStock);
			
			
			
			
			
			
			
			
			
//			User User = (User) session.getAttribute("user");
//			//セッションデーの有無
//			if (User == null) {
//				error = "セッション切れの為、購入は出来ません。";
//				cmd = "logout";
//				return;
//			}
//			
//			//ArrayListのOrderセッション取得と有無
//			ArrayList<OrderItem> OrderItemList = (ArrayList<OrderItem>) session.getAttribute("order_list");
//			if (OrderItemList == null) {
//				error = "カートの中に何も無かったので購入は出来ません。";
//				cmd = "menu";
//				return;
//			}
//			
//			
//			//インスタンス化
//			ItemDAO ItemDao = new ItemDAO();
//			orderItemDAO orderItemDao = new orderItemDAO();
//
//			//bookList生成
//			ArrayList<Item> itemList = new ArrayList<Item>();
//
//			//orderListをorderとbookListに格納
//			for (int i = 0; i < OrderItemList.size(); i++) {
//				OrderItem OrderItem = OrderItemList.get(i);
//				Item Item = ItemDao.selectByIsbn(OrderItem.getIsbn());
//				orderItemDao.insert(OrderItem);
//				itemList.add(Item);
//			}
//			
//			//リクエストスコープでbookListに格納
//			request.setAttribute("item_list", itemList);
//			
//			//情報をクリアにする
//			session.setAttribute("order_list", null);
			
			

		} catch (IllegalStateException e) {
			error =e+ "DB接続エラーの為、購入は出来ません。";
			cmd = "logout";

		} catch (Exception e) {
			error = e+"予期せぬエラーが発生しました。";
			cmd = "logout";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合はouyConfirm.jspにフォワード
				request.setAttribute("cmd","purchase");
				request.getRequestDispatcher("/view/orderConfirm.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/userError.jsp").forward(request, response);
			}
		}

	}
}