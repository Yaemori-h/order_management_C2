package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderItem;

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
	
	
	//purchaseになったので、buyinfoから取り出してください。(八重森)
	
	public ArrayList<OrderItem> selectAll() {
		Connection con = null;
		Statement smt = null;
		ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();

		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM orderinfo WHERE order_id ";
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				OrderItem OrderItem = new OrderItem();
				OrderItem.setbuyinfo_id(rs.getInt("buyinfo_id"));
				OrderItem.setquantity(rs.getString("note"));
				OrderItem.setnote(rs.getInt("quantity"));
				OrderItem.settotal(rs.getInt("date"));
				OrderItem.setorder_date(rs.getInt("total"));
				OrderItem.setorder_date(rs.getInt("order_date"));
				orderList.add(OrderItem);
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
		return orderList;
	}


}