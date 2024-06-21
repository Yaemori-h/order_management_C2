package bean;

public class purchaseInfo {
	//フィールド変数の定義
	//購入情報ID
	private int buyinfo_id;
	//個数
	private int quantity;
	//備考欄
	private String note;
	//合計金額
	private int total;
	//発注日
	private int order_date;

	//コンストラクタの定義(初期設定）
	public purchaseInfo() {
		this.buyinfo_id = 0;
		this.quantity = 0;
		this.note = null;
		this.total = 0;
		this.order_date = 0;
	}

	//各変数のgetメソッドの定義
	//購入情報ID
	public int getbuyinfo_id() {
		return buyinfo_id;
	}

	//個数
	public int getquantity() {
		return quantity;
	}

	//備考欄
	public String getnote() {
		return note;
	}

	//合計金額
	public int gettotal() {
		return total;
	}

	//発注日
	public int getorder_date() {
		return order_date;
	}
	
	//各変数のsetメソッドの定義
	//購入情報ID
	public void setbuyinfo_id(int buyinfo_id) {
		this.buyinfo_id = buyinfo_id;
	}
	//個数
	public void setquantity(String note) {
		this.note = note;
	}
	//備考欄
	public void setnote(int quantity) {
		this.quantity = quantity;
	}
	//合計金額
	public void settotal(int total) {
		this.total = total;
	}
	//発注日
	public void setorder_date(int order_date) {
		this.order_date = order_date;
	}

}