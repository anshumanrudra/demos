package com.inventory.config;

import java.util.Scanner;

import com.inventory.model.Item;
import com.inventory.services.InventoryService;
import com.inventory.services.InventoryServiceImpl;

/**
 * Demo - Inventory Management
 * @author srinivasan
 */
public class Main {

	static String CMD_CREATE = "create";
	static String CMD_UPDATE_BUY = "updateBuy";
	static String CMD_UPDATE_SELL = "updateSell";
	static String CMD_UPDATE_SELL_PRICE = "updateSellPrice";
	static String CMD_DELETE = "delete";
	static String CMD_REPORT = "report";
	
	private InventoryService inventoryService;
	
	Main(){
		this.inventoryService = new InventoryServiceImpl();
	}
	
	public static void main(String[] args) {
		System.out.println("---Inventory Managment---");
		System.out.println("Enter inputs to manage items. Enter 'exit' to quit the application");
		
		Main main = new Main();
		Scanner scanner = new Scanner(System.in);
		String input;
		/** Receive input from user till enter 'exit' command*/
		while(!(input = scanner.nextLine()).equalsIgnoreCase("Exit")){
			main.run(input);
		}
		scanner.close();
		System.out.println("Good Bye.");
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
