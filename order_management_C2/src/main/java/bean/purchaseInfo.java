package bean;

public class purchaseInfo {
	//フィールド変数の定義
			//購入情報ID
			private int buyinfoId;
			//個数
			private int quantity;
			//備考欄
			private String note;
			//合計金額
			private int total;
			//発注日
			private int orderDate;

			//コンストラクタの定義(初期設定）
			public purchaseInfo() {
				this.buyinfoId = 0;
				this.quantity = 0;
				this.note = null;
				this.total = 0;
				this.orderDate = 0;
			}

			//各変数のgetメソッドの定義
			//購入情報ID
			public int getBuyinfoId() {
				return buyinfoId;
			}

			//個数
			public int getQuantity() {
				return quantity;
			}

			//備考欄
			public String getNote() {
				return note;
			}

			//合計金額
			public int getTotal() {
				return total;
			}

			//発注日
			public int getOrderDate() {
				return orderDate;
			}
			
			//各変数のsetメソッドの定義
			//購入情報ID
			public void setBuyinfoId(int buyinfo_id) {
				this.buyinfoId = buyinfo_id;
			}
			//個数
			public void setQuantity(int quantity) {
				this.quantity = quantity;
			}
			//備考欄
			public void setNote(String note) {
				this.note = note;
			}
			//合計金額
			public void setTotal(int total) {
				this.total = total;
			}
			//発注日
			public void setOrderDate(int order_date) {
				this.orderDate = order_date;
			}
}