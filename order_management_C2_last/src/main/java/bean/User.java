/*//DTO：データの入れ物としてのクラス(ユーザー情報)
 * 作成予定日:6/21
 * 作成終了予定日:6/21
 * 作成開始日:6/21
 * 作成終了日:6/21
// * テスト終了日:
 * 作成者:kim
 */

package bean;

public class User {
	
	private int userid; // ユーザー名
	private String email; //メールアドレス
	private String password; // パスワード
	private String name; //名前
	private String address; //住所
	
	//コンストラクタ
	public User() {
		this.userid = 0;
		this.email = null;
		this.password= null;
		this.name= null;
		this.address= null;
	}
	
	//Getterメソッド
	
	public int getUserid() {
		return this.userid;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	//Setterメソッド
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}