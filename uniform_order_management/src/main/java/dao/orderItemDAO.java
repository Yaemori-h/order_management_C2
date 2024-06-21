package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderItem;


public class orderItemDAO {
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

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
	
	
	public ArrayList<OrderItem> selectAll() {
		Connection con = null;
		Statement smt = null;
		ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();

		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT buyinfo.buyinfo_id,userinfo.user_id,iteminfo.item_id\n"
					+ "FROM((buyinfo LEFT OUTER JOIN userinfo ON buyinfo.buyinfo_id=userinfo.buyinfo_id)\n"
					+ "LEFT OUTER JOIN iteminfo ON buyinfo.buyinfo_id)";
			ResultSet rs = smt.executeQuery(sql);
			
			while (rs.next()) {
				OrderItem orderitem =new OrderItem();
				
				orderitem.setBuyinfoid(rs.getInt("buyinfo_id"));
				orderitem.setUserid(rs.getInt("user_id"));
				orderitem.setItemid(rs.getInt("item_id"));
				//すべての情報を取り出せていません。setメソッドがコピペのままです。（八重森）＜ー書いてあったところは直しました。
				orderList.add(orderitem);
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

	
	//②注文を登録するメソッド
	
	
	
	//③注文を更新するメソッド
	
}
