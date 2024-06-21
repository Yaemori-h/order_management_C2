package bean;

public class OrderItem {
	//orderid
	private int orderid;

	//buyinfo_id
	private int buyinfoid;

	//user_id
	private int userid;

	//item_id
	private int itemid;

	//deposit_status
	private String depositstatus;

	//sending_status
	private String sendingstatus;

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

} 