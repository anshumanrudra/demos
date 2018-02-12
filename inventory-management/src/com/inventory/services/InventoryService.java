package com.inventory.services;

import java.util.Map;

import com.inventory.model.Item;

/**
 * @author srinivasan
 *
 */
public interface InventoryService {
	
	Map<String, Item> findAll();
	
	Item addItem(Item item);
	
	Item getItem(String itemName);
	
	Item updateBuyItem(String itemName, int buyQty);
	
	Item updateSellItem(String itemName, int sellQty);
	
	Item deleteItem(String itemName);

	void generateReport();
	
}
