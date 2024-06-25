/* 作成者；八重森
 * 日時：6/21
 * 
 */


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Manager;

public class managerDAO {
	//データベース接続情報
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASS = "root123";

	private static Connection getConnection() {//DB接続メソッド
		try {

			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//manager_idから管理者の情報を取得するメソッド
	public Manager selectById(String manager_id) {

		Connection con = null;
		Statement smt = null;

		Manager manager = new Manager();

		String sql = "SELECT * FROM managerinfo WHERE manager_id = '" + manager_id + "'";

		try {
			con = managerDAO.getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				manager.setManagerId(rs.getString("manager_id"));
				manager.setManagerPassword(rs.getString("manager_password"));
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
		return manager;
	}
}
