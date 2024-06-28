/* 作成者　八重森
 * 日時　6/21
 * 
 */



package bean;

public class Manager {
	
	private String managerId;
	private String managerPassword;
	
	//アクセサメソッド
	public String getManagerId() {
		return this.managerId;
	}
	
	public String getManagerPassword() {
		return this.managerPassword;
	}
	
	public void setManagerId(String managerId) {
		this.managerId=managerId;
	}
	
	public void setManagerPassword(String managerPassword) {
		this.managerPassword=managerPassword;
	}
	

}
