package com.inventory.util;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.inventory.model.Item;

/**
 * @author srinivasan
 *
 */
public class InventoryUtil {
	
	/**
	 * Generate Report
	 */
	public static void printReport(List<Item> items, double profitValue){
		Collections.sort(items, (item1, item2)->{
        	return item1.getItemName().compareTo(item2.getItemName());
        });
		DecimalFormat df = new DecimalFormat("##0.00");
	    System.out.format("%-20s %-20s %-20s %-20s %-20s", "Item Name", "Bought At", "Sold At", "AvailableQty", "Value");
	    System.out.println();
	    System.out.format("%-20s %-20s %-20s %-20s %-20s", "---------", "---------", "-------", "------------", "--------");
	    System.out.println();
	    AtomicReference<Double> totalValue = new AtomicReference<>(new Double(0.0));
	    items.forEach(item->{
	    	System.out.format("%-20s %-20s %-20s %-20d %-20s", 
	                item.getItemName(), df.format(item.getCostPrice()), df.format(item.getSellingPrice()), 
	                item.getAvailableQty(), df.format(item.getCostPrice() * item.getAvailableQty()));
	        System.out.println();
	        totalValue.set(totalValue.get().doubleValue()+item.getCostPrice() * item.getAvailableQty());
	    });
	    System.out.println("--------------------------------------------------------------------------------------------");
	    System.out.format("%-83s %-17s", "Total value", df.format(totalValue.get()));
	    System.out.println();
	    System.out.format("%-83s %-17s", "Profit since previous report", df.format(profitValue));
	    System.out.println();
	}
		
}
