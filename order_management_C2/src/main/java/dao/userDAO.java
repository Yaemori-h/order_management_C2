/* DAO：データアクセスを行うクラス(ユーザー情報)
 * 作成予定日:6/21
 * 作成終了予定日:6/21
 * 作成開始日:6/21
 * 作成終了日:6/21
/ * テスト終了日:
 * 作成者:kim
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;

public class userDAO {
	//DB情報をフィールド変数に定義
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	private static Connection getConnection() { //DB接続を行うメソッド
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			System.out.println(e); //チェック用
			throw new IllegalStateException(e);

		}
	}

	//データベースにユーザーデータを登録するメソッド(初回登録)
	public void insert(User user) {
		Connection con = null;
		Statement smt = null;
		String sql = "INSERT INTO userinfo VALUES(NULL,'" + user.getEmail() + "','"
				+ user.getPassword() + "','" +  user.getAddress()+ "','" + user.getName()
				+ "')";
		
		try {
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql); //executeUpdate（）メソッドを利用して、SQL文を発行し書籍データを登録

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
	}

	//DBのテーブルから指定のユーザーidに紐づくすべての要素情報を引っ張って来るメソッド
	public User selectByUser(int userid) {
		Connection con = null;
		Statement smt = null;
		User user = new User();
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT * FROM userinfo WHERE user_id ='" + userid + "'";
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) { //結果セットからユーザーデータを取り出し、オブジェクトに格納
				user.setEmail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setName("name");
				user.setAddress("address");
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally { //オブジェクトをクローズ
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
		return user;
	}

	//指定のユーザーとパスワードの条件に合致する情報を取得するメソッド(ログイン)
	public User selectByUser(String mail, String password) {
		Connection con = null;
		Statement smt = null;
		User user = new User();
		String sql = "SELECT * FROM userinfo WHERE mail ='" + mail + "' AND password='" + password + "'";

		try {
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) { //結果セットからユーザーデータを取り出し、オブジェクトに格納
				user.setUserid(rs.getInt("user_id"));
				user.setEmail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
			}
			return user;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally { //オブジェクトをクローズ
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
	
	//引数のメールアドレスから該当する列を探し戻り値としてuser情報を返すメソッド(重複チェック用)
	public User selectByUser(String mail) {
		Connection con = null;
		Statement smt = null;
		User user = new User();
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT * FROM userinfo WHERE mail ='" + mail + "'";
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) { //結果セットからユーザーデータを取り出し、オブジェクトに格納
				user.setEmail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setName("name");
				user.setAddress("address");
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally { //オブジェクトをクローズ
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
		return user;
	}

}