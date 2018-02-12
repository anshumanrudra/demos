package com.inventory.config;

import java.util.ArrayList;
import java.util.List;

import com.inventory.model.Item;
import com.inventory.services.InventoryService;
import com.inventory.services.InventoryServiceImpl;

public class UnitTest {

	static String CMD_CREATE = "create";
	static String CMD_UPDATE_BUY = "updateBuy";
	static String CMD_UPDATE_SELL = "updateSell";
	static String CMD_UPDATE_SELL_PRICE = "updateSellPrice";
	static String CMD_DELETE = "delete";
	static String CMD_REPORT = "report";
	
	private InventoryService inventoryService;
	
	UnitTest(){
		inventoryService = new InventoryServiceImpl();
	}
	
	public static void main(String[] args) {
		System.out.println("---Inventory Managment---");
		
		UnitTest unitTest = new UnitTest();
		
		List<String> testInputs = new ArrayList<>();
		testInputs.add("create Book01 10.50 13.79");
		testInputs.add("create Food01 1.47 3.98");
		testInputs.add("create Med01 30.63 34.29");
		testInputs.add("create Tab01 57.00 84.98");
		testInputs.add("updateBuy Tab01 100");
		testInputs.add("updateSell Tab01 2");
		testInputs.add("updateBuy Food01 500");
		testInputs.add("updateBuy Book01 100");
		testInputs.add("updateBuy Med01 100");
		testInputs.add("updateSell Food01 1");
		testInputs.add("updateSell Food01 1");
		testInputs.add("updateSell Tab01 2");
		testInputs.add("report");
		testInputs.add("delete Book01");
		testInputs.add("updateSell Tab01 5");
		testInputs.add("create Mobile01 10.51 44.56");
		testInputs.add("updateBuy Mobile01 250");
		testInputs.add("updateSell Food01 5");
		testInputs.add("updateSell Mobile01 4");
		testInputs.add("updateSell Med01 10");
		testInputs.add("report");
		/** Test case F */
		//testInputs.add("updateSell Med01 10");
		//testInputs.add("updateSellPrice Med01 50.25");
		//testInputs.add("updateSell Med01 10");
		//testInputs.add("report");

		testInputs.forEach(
				input->{
					unitTest.run(input);
		});
	}
	
	/** Accepts user command and maintains Inventory */
	void run(String input) {

		String inputs[] = input.split("\\s");
		String command = inputs[0];
		
		try{
			if(CMD_CREATE.equals(command)){
				String itemName = inputs[1];
				double costPrice = Double.parseDouble(inputs[2]);
				double sellingPrice = Double.parseDouble(inputs[3]);
				
				Item item = new Item(itemName, costPrice, sellingPrice);
				inventoryService.addItem(item);
				System.out.println("Item added successfully");
			}else if(CMD_UPDATE_BUY.equals(command)){
				String itemName = inputs[1];
				int buyQty = Integer.parseInt(inputs[2]);
				
				Item item = inventoryService.updateBuyItem(itemName, buyQty);
				if(item != null){
					System.out.println("Item updated successfully");
				}else{
					System.out.println("Item not found");
				}
			}else if(CMD_UPDATE_SELL.equals(command)){
				String itemName = inputs[1];
				int sellQty = Integer.parseInt(inputs[2]);
				
				Item item = inventoryService.updateSellItem(itemName, sellQty);
				if(item != null){
					System.out.println("Item updated successfully");
				}else{
					System.out.println("Item not found");
				}
				System.out.println("Item updated successfully");

			}else if(CMD_UPDATE_SELL_PRICE.equals(command)){
				String itemName = inputs[1];
				double newSellPrice = Double.parseDouble(inputs[2]);
				Item currentItem = inventoryService.getItem(itemName);
				if(currentItem != null){
					currentItem.setSellingPrice(newSellPrice);
					inventoryService.addItem(currentItem); //optional call
					System.out.println("Item updated successfully");
				}else{
					System.out.println("Item not found");
				}
			}else if(CMD_DELETE.equals(command)){
				String itemName = inputs[1];
				
				Item item = inventoryService.deleteItem(itemName);
				if(item != null){
					System.out.println("Item deleted successfully");
				}else{
					System.out.println("Item not found");
				}
			}else if(CMD_REPORT.equals(command)){
				inventoryService.generateReport();
			}else {
				System.out.println("Not a valid command");
			}
		}catch(Exception e){
			System.err.println("Unexpected Error: "+e.getMessage());
			System.out.println("Continue with valid input");
		}
	}

}
