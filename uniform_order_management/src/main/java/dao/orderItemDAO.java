/**
 * orderItemDAO
 * 
 * 作成日：2024/6/21
/ * 作成者：尹昭喜
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	//受注一覧用リスト
		public ArrayList<OrderItem> selectAll() {
			Connection con = null;
			Statement smt = null;
			ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
			try {
				con = getConnection();
				smt = con.createStatement();

				String sql = "SELECT orderinfo.order_id, orderinfo.buyinfo_id, orderinfo.user_id, orderinfo.item_id, orderinfo.deposit_status, orderinfo.sending_status, userinfo.name, iteminfo.item_name, buyinfo.quantity, buyinfo.total, buyinfo.order_date "
						+
						"FROM orderinfo " +
						"JOIN userinfo ON orderinfo.user_id = userinfo.user_id " +
						"JOIN iteminfo ON orderinfo.item_id = iteminfo.item_id " +
						"JOIN buyinfo ON orderinfo.buyinfo_id = buyinfo.buyinfo_id";
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


//public OrderItem selectByItem(String orderid) {
//
//	Connection con = null;
//	Statement smt = null;
//	OrderItem orderItem = new OrderItem();
//	
//	try {
//		String sql = "SELECT * FROM orderinfo WHERE order_id='" + orderid + "'";
//		con = getConnection();
//		smt = con.createStatement();
//		ResultSet rs = smt.executeQuery(sql);
//	
//		while (rs.next()) {
//			orderItem.setOrderid(rs.getInt("orderid"));
//			orderItem.setBuyinfoid(rs.getInt("buyinfoid"));
//			orderItem.setUserid(rs.getInt("userid"));
//			orderItem.setItemid(rs.getInt("itemid"));
//			orderItem.setDepositstatus(rs.getString("depositstatus"));
//			orderItem.setSendingstatus(rs.getString("sendingstatus"));
//		}
//	} catch (Exception e) {
//		throw new IllegalStateException(e);
//	} finally {
//		try {
//			if (smt != null) {smt.close();}
//			if (con != null) {con.close();}
//		} catch (SQLException ignore) {}
//	}
//	return orderItem;
//}

//指定されたorder_idに基づいてOrderItemを取得するメソッド
	public OrderItem selectByOrderId(int order_id) {
		Connection con = null;
		PreparedStatement ps = null;
		OrderItem orderItem = null;

		try {
			con = getConnection();
			String sql = "SELECT orderinfo.order_id, userinfo.name, userinfo.address, userinfo.mail, iteminfo.item_name, buyinfo.quantity, buyinfo.note "
					+ "FROM orderinfo "
					+ "JOIN userinfo ON orderinfo.user_id = userinfo.user_id "
					+ "JOIN iteminfo ON orderinfo.item_id = iteminfo.item_id "
					+ "JOIN buyinfo ON orderinfo.buyinfo_id = buyinfo.buyinfo_id "
					+ "WHERE orderinfo.order_id = '" + order_id + "'";
			//con smt
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				orderItem = new OrderItem();
				orderItem.setOrderid(rs.getInt("order_id"));
				orderItem.setUserid(rs.getInt("user_id"));
				orderItem.setItemid(rs.getInt("item_id"));
				orderItem.setBuyinfoid(rs.getInt("buyinfo_id"));

				User user = new User();
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("mail"));

				Item item = new Item();
				item.setItem_name(rs.getString("item_name"));

				orderItem.setUser(user);
				orderItem.setItem(item);
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setTotal(rs.getInt("total"));
				orderItem.setOrderDate(rs.getDate("order_date"));
				orderItem.setNote(rs.getString("note"));
			}

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
}




