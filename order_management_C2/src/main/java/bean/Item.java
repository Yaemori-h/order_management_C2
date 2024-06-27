/**
 *DTO:Item 商品
 *
 * 作成日：2024/6/21
 * 作成者：南部優実
 * */

package bean;

public class Item {

	private int item_id; //商品ID
	private String item_name; //商品名
	private int price; //価格
	private int stock; //在庫数

	public Item() {
		this.item_id = 0;
		this.item_name = null;
		this.price = 0;
		this.stock = 0;
	}
	
	//商品ID
	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	//商品名
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	//価格
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	//	在庫数
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}