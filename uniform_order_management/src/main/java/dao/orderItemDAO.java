/**
 * orderItemDAO
 * 
 * 作成日：2024/6/21
/ * 作成者：尹昭喜
 */

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

	//orderinfoテーブルから全情報を取得するメソッド	
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
				OrderItem orderitem = new OrderItem();
				orderitem.setOrderid(rs.getInt("order_id"));
				orderitem.setBuyinfoid(rs.getInt("buyinfo_id"));
				orderitem.setUserid(rs.getInt("user_id"));
				orderitem.setItemid(rs.getInt("item_id"));
				orderitem.setDepositstatus(rs.getString("deposit_status"));
				orderitem.setSendingstatus(rs.getString("sending_status"));

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

	//受注情報を登録するメソッド
	public void insert(OrderItem _orderitem) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "INSERT INTO orderinfo(order_id,buyinfo_id,user_id,item_id,deposit_status,sending_status)VALUES('"
					+ _orderitem.getOrderid()
					+ "','" + _orderitem.getBuyinfoid()
					+ "','" + _orderitem.getUserid()
					+ "','" + _orderitem.getItemid()
					+ "','" + _orderitem.getDepositstatus()
					+ "','" + _orderitem.getSendingstatus() + ")";
			smt.executeUpdate(sql);
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
	}

	//受注情報を変更メソッド
	public void update(OrderItem _orderitem) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE orderinfo SET" + "deposit_status ='" + _orderitem.getDepositstatus() + "',"
					+ " sending_status =" + _orderitem.getSendingstatus()
					+ " WHERE order_id ='" + _orderitem.getOrderid() + "'";
			smt.executeUpdate(sql);

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
	}
}
