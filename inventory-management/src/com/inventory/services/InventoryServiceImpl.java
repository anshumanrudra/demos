package com.inventory.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventory.model.Item;
import com.inventory.util.InventoryUtil;

/**
 * @author srinivasan
 *
 */
public class InventoryServiceImpl implements InventoryService {
	
	//Demo purpose - Items are maintained in the object level
	private Map<String, Item> itemsMap = new HashMap<>();
	private Map<String, Double> sellItemsMap = new HashMap<>();
	private List<Item> deletedItems = new ArrayList<>();

	/**
	 * Get all Inventories
	 */
	@Override
	public Map<String, Item> findAll() {
		return this.itemsMap;
	}
	
	/**
	 * Add Item to Inventory
	 */
	@Override
	public Item addItem(Item item) {
		return itemsMap.put(item.getItemName(), item);
	}

	/**
	 * Get the Item from Inventory
	 */
	@Override
	public Item getItem(String itemName) {
		return itemsMap.get(itemName);
	}

	/**
	 * Update buying quantity in Inventory
	 */
	@Override
	public Item updateBuyItem(String itemName, int buyQty) {
		Item item = getItem(itemName);
		if(item != null){
			item.setAvailableQty(item.getAvailableQty() + buyQty);
			return item;
		}
		return null;
	}
	
	/**
	 * Update selling quantity in Inventory
	 */
	@Override
	public Item updateSellItem(String itemName, int sellQty) {
		Item item = getItem(itemName);
		if(item != null){
			item.setAvailableQty(item.getAvailableQty() - sellQty);
			double profit = sellItemsMap.get(itemName) != null ? sellItemsMap.get(itemName) : 0;
			sellItemsMap.put(itemName, profit + (item.getSellingPrice()-item.getCostPrice()) * sellQty);
			return item;
		}
		return null;
	}

	/**
	 * Delete the Item from Inventory
	 */
	@Override
	public Item deleteItem(String itemName) {
		Item item = getItem(itemName);
		if(item != null){
			deletedItems.add(item);
			return this.itemsMap.remove(itemName);
		}
		return null;
	}

	/**
	 * Generate Report
	 */
	@Override
	public void generateReport() {
		double soldItemsValue = sellItemsMap.values().stream().mapToDouble(Double::doubleValue).sum();
		double deleteItemsValue = 0.0;
		for (Item item : deletedItems) {
			deleteItemsValue = item.getAvailableQty() * item.getCostPrice();
		}
		InventoryUtil.printReport(new ArrayList<>(findAll().values()), (soldItemsValue-deleteItemsValue));
		deletedItems.clear();
		sellItemsMap.clear();
	}

}
