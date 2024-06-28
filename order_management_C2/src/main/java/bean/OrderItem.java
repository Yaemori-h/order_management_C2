package bean;

import java.util.Date;

public class OrderItem {
	//受注ID
	private int orderid;

	//購入情報ID
	private int buyinfoid;

	//ユーザーID
	private int userid;

	//商品ID
	private int itemid;

	//入金状況）
	private String depositstatus;

	//発送状況
	private String sendingstatus;

	//氏名
	private User user;

	//商品
	private Item item;

	//個数
	private int quantity;

	//合計金額
	private int total;

	//発注日
	private Date orderDate;
	
	//備考欄
	private String note;
	
	//追加　購入情報
    private purchaseInfo purchaseInfo;
    
    public OrderItem(){
    	this.orderid=0;
    }


	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getBuyinfoid() {
		return buyinfoid;
	}

	public void setBuyinfoid(int buyinfoid) {
		this.buyinfoid = buyinfoid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getDepositstatus() {
		return depositstatus;
	}

	public void setDepositstatus(String depositstatus) {
		this.depositstatus = depositstatus;
	}

	public String getSendingstatus() {
		return sendingstatus;
	}

	public void setSendingstatus(String sendingstatus) {
		this.sendingstatus = sendingstatus;
	}

	// User
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Item
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	// quantity
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// total
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	// orderDate
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	//備考欄
	public String getNote() {
		return note;
	}
	
	//備考欄
	public void setNote(String note) {
		this.note = note;
	}
	
	//追加　購入情報
	public purchaseInfo getPurchaseInfo() {
		return this.purchaseInfo;
	}
	
	public void setPurchaseInfo(purchaseInfo  purchaseInfo) {
		this.purchaseInfo=purchaseInfo;
	}
	
}