package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.purchaseInfo;

public class purchaseInfoDAO {
	//DB情報をフィールド変数に定義
		//JDBCドライバ内部のDriverクラスパス
		private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";

		//接続するMySQLデータベースパス
		private static final String URL = "jdbc:mariadb://localhost/uniformdb";

		// データベースのユーザー名
		private static final String USER = "root";

		// データベースのパスワード
		private static final String PASSWD = "root123";

		//①情報を元にDB接続を行うメソッド定義
		// フィールド変数の情報を基に、DB接続をおこなうメソッド
		private static Connection getConnection() {
			Connection con = null;
			try {
				Class.forName(RDB_DRIVE);
				con = DriverManager.getConnection(URL, USER, PASSWD);
				return con;
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		/**
		 * 購入情報を取得するメソッド定義
		 * 呼び出し元：
		 */
		public ArrayList<purchaseInfo> selectAll() {
			Connection con = null;
			Statement smt = null;
			ArrayList<purchaseInfo> PurchaseList = new ArrayList<purchaseInfo>();

			try {
				con = getConnection();
				smt = con.createStatement();

				String sql = "SELECT * FROM buyinfo WHERE buyinfo_id ";
				ResultSet rs = smt.executeQuery(sql);
				while (rs.next()) {
					purchaseInfo PurchaseItem = new purchaseInfo();
					PurchaseItem.setbuyinfo_id(rs.getInt("buyinfo_id"));
					PurchaseItem.setquantity(rs.getString("note"));
					PurchaseItem.setnote(rs.getInt("quantity"));
					PurchaseItem.setorder_date(rs.getInt("total"));
					PurchaseItem.setorder_date(rs.getInt("order_date"));
					PurchaseList.add(PurchaseItem);
				}

			} catch (Exception e) {
				throw new IllegalStateException(e);
			} finally {
				if (smt != null) {
					try {
						smt.close();
					} catch (SQLException ignore) {
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException ignore) {
					}
				}
			}
			return PurchaseList;
		}


}