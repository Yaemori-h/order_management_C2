package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
					PurchaseItem.setBuyinfoId(rs.getInt("buyinfo_id"));
					PurchaseItem.setQuantity(rs.getInt("quantity"));
					PurchaseItem.setNote(rs.getString("note"));
					PurchaseItem.setTotal(rs.getInt("total"));
					PurchaseItem.setOrderDate(rs.getInt("order_date"));
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
		public purchaseInfo selectById(int buyinfoId) {

			Connection con = null;
			Statement smt = null;
			purchaseInfo purchaseInfo = new purchaseInfo();

			try {
				String sql = "SELECT * FROM buyinfo WHERE buyinfo_id='" + buyinfoId + "'";
				con = getConnection();
				smt = con.createStatement();
				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					purchaseInfo PurchaseItem = new purchaseInfo();
					PurchaseItem.setBuyinfoId(rs.getInt("buyinfo_id"));
					PurchaseItem.setQuantity(rs.getInt("quantity"));
					PurchaseItem.setNote(rs.getString("note"));
					PurchaseItem.setTotal(rs.getInt("total"));
					PurchaseItem.setOrderDate(rs.getInt("order_date"));
				}
			} catch (Exception e) {
				throw new IllegalStateException(e);
			} finally {
				try {
					if (smt != null) {
						smt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException ignore) {
				}
			}
			return purchaseInfo;
		}
		
		
		//purchaseInfoに登録するメソッド
		public int insert(purchaseInfo purchaseInfo) {
			Connection con = null;
			Statement smt = null;
			String sql = "INSERT INTO buyinfo VALUES(NULL,"
					+ purchaseInfo.getQuantity() + ",'"
					+ purchaseInfo.getNote() + "',"
					+ purchaseInfo.getTotal() 
					+ ",CURDATE())";
			
			int autoIncrementKey = 0;
			try {
				con = purchaseInfoDAO.getConnection();
				smt = con.createStatement();
				
				PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				
				stmt.executeUpdate(); 
				
				ResultSet res = stmt.getGeneratedKeys();
				
				if(res.next()){
		             autoIncrementKey = res.getInt(1);
		         }
				
				

			} catch (Exception e) {
				throw new IllegalStateException(e);
			} finally {//オブジェクトをクローズ
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
			return autoIncrementKey;
		}

}