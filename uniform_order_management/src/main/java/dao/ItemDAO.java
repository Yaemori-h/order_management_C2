/**
 * DAO：ItemDAO
 * 
 * 作成日：2024/6/21
/ * 作成者：南部優実
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Item;


public class ItemDAO {
	
	//SQL
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

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
	
	//一覧(ListServlet)
		public ArrayList<Item> selectAll() {
			Connection con = null;
			Statement smt = null;
			ArrayList<Item> itemList = new ArrayList<Item>();

			try {
				//SQL接続
				con = getConnection();
				smt = con.createStatement();

				//SQL文とSQLをDBへ発行
				String sql = "SELECT * FROM iteminfo  ORDER BY item_id";
				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					Item Item = new Item();
					Item.setItem_id(rs.getInt("item_id"));
					Item.setItem_name(rs.getString("item_name"));
					Item.setPrice(rs.getInt("price"));
					Item.setStock(rs.getInt("stock"));
					itemList.add(Item);
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
			return itemList;
		}
	
	

}