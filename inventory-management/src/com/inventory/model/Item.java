package com.inventory.model;

/**
 * @author srinivasan
 *
 */
public class Item {
	
	private String itemName;
	private double costPrice;
	private double sellingPrice;
	private int availableQty;
	
	public Item(String itemName, double costPrice, double sellingPrice) {
		this.itemName = itemName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice
				+ ", availableQty=" + availableQty + "]";
	}

}
