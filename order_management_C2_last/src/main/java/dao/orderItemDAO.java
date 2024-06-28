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

import bean.Item;
import bean.OrderItem;
import bean.User;

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
	
	// 受注一覧用リスト
	public ArrayList<OrderItem> selectAll() {
		Connection con = null;
		Statement smt = null;
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT orderinfo.order_id, orderinfo.buyinfo_id, orderinfo.user_id, orderinfo.item_id, orderinfo.deposit_status, orderinfo.sending_status, userinfo.name, userinfo.address, userinfo.mail, iteminfo.item_name, buyinfo.quantity, buyinfo.total, buyinfo.order_date "
					+ "FROM orderinfo "
					+ "JOIN userinfo ON orderinfo.user_id = userinfo.user_id "
					+ "JOIN iteminfo ON orderinfo.item_id = iteminfo.item_id "
					+ "JOIN buyinfo ON orderinfo.buyinfo_id = buyinfo.buyinfo_id "
					+ " ORDER BY orderinfo.order_id DESC";
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				OrderItem order = new OrderItem();
				order.setOrderid(rs.getInt("order_id"));
				order.setBuyinfoid(rs.getInt("buyinfo_id"));
				order.setUserid(rs.getInt("user_id"));
				order.setItemid(rs.getInt("item_id"));
				order.setDepositstatus(rs.getString("deposit_status"));
				order.setSendingstatus(rs.getString("sending_status"));

				User user = new User();
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("mail"));

				Item item = new Item();
				item.setItem_name(rs.getString("item_name"));
				item.setPrice(rs.getInt("total"));

				order.setUser(user);
				order.setItem(item);
				order.setQuantity(rs.getInt("quantity"));
				order.setTotal(rs.getInt("total"));
				order.setOrderDate(rs.getDate("order_date"));

				orderItemList.add(order);
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
		return orderItemList;
	}

	// 受注情報を登録するメソッド
	public void insert(OrderItem orderitem) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "INSERT INTO orderinfo (buyinfo_id, user_id, item_id, deposit_status, sending_status) VALUES ("
					+ orderitem.getBuyinfoid() + ", "
					+ orderitem.getUserid() + ", "
					+ orderitem.getItemid() + ", '"
					+ orderitem.getDepositstatus() + "', '"
					+ orderitem.getSendingstatus() + "')";
			smt.executeUpdate(sql);
		} catch (SQLException e) {
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

	// 受注情報変更メソッド
	public void update(OrderItem orderitem) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE orderinfo SET deposit_status = '" + orderitem.getDepositstatus() + "', "
					+ "sending_status = '" + orderitem.getSendingstatus() + "' "
					+ "WHERE order_id = " + orderitem.getOrderid();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
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

	// 指定されたorder_idに基づいてOrderItemを取得するメソッド
	public OrderItem selectByOrderId(int order_id) {
		Connection con = null;
		Statement smt = null;
		OrderItem orderItem = null;

		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT orderinfo.order_id, orderinfo.user_id, orderinfo.item_id, orderinfo.buyinfo_id, " +
					"orderinfo.deposit_status, orderinfo.sending_status, " +
					"userinfo.name, userinfo.address, userinfo.mail, " +
					"iteminfo.item_name, buyinfo.quantity, buyinfo.note " +
					"FROM orderinfo " +
					"JOIN userinfo ON orderinfo.user_id = userinfo.user_id " +
					"JOIN iteminfo ON orderinfo.item_id = iteminfo.item_id " +
					"JOIN buyinfo ON orderinfo.buyinfo_id = buyinfo.buyinfo_id " +
					"WHERE orderinfo.order_id = " + order_id;

			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				orderItem = new OrderItem();
				orderItem.setOrderid(rs.getInt("order_id"));
				orderItem.setUserid(rs.getInt("user_id"));
				orderItem.setItemid(rs.getInt("item_id"));
				orderItem.setBuyinfoid(rs.getInt("buyinfo_id"));
				orderItem.setDepositstatus(rs.getString("deposit_status"));
				orderItem.setSendingstatus(rs.getString("sending_status"));

				User user = new User();
				user.setUserid(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("mail"));

				Item item = new Item();
				item.setItem_name(rs.getString("item_name"));

				orderItem.setUser(user);
				orderItem.setItem(item);
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setNote(rs.getString("note"));
			}

		} catch (SQLException e) {
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

		return orderItem;
	}
	
	// 今月および前月の売り上げを取得するメソッド
		public int[] getMonthlySales() {
			Connection con = null;
			Statement smt = null;
			int[] sales = new int[2]; // [0] 今月の売り上げ, [1] 前月の売り上げ

			try {
				con = getConnection();
				smt = con.createStatement();

				// 今月の売り上げ
				String sqlThisMonth = "SELECT SUM(buyinfo.total) as total FROM orderinfo " +
						"JOIN buyinfo ON orderinfo.buyinfo_id = buyinfo.buyinfo_id " +
						"WHERE MONTH(buyinfo.order_date) = MONTH(CURRENT_DATE) " +
						"AND YEAR(buyinfo.order_date) = YEAR(CURRENT_DATE)";
				ResultSet rsThisMonth = smt.executeQuery(sqlThisMonth);
				if (rsThisMonth.next()) {
					sales[0] = rsThisMonth.getInt("total");
				}

				// 前月の売り上げ
				String sqlLastMonth = "SELECT SUM(buyinfo.total) as total FROM orderinfo " +
						"JOIN buyinfo ON orderinfo.buyinfo_id = buyinfo.buyinfo_id " +
						"WHERE MONTH(buyinfo.order_date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) " +
						"AND YEAR(buyinfo.order_date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)";
				ResultSet rsLastMonth = smt.executeQuery(sqlLastMonth);
				if (rsLastMonth.next()) {
					sales[1] = rsLastMonth.getInt("total");
				}

			} catch (SQLException e) {
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

			return sales;
		}
}




