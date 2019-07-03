package com.coffeeshop.coffeeshop;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeShop {

	public static void main(String[] args) {
		printWelcomeMessage(getCustomerName());
		Map<String, Integer> orderMap = processInput(getMenu());
		printBill(getMenu(), orderMap);
	}

	public static Map<String, Integer> processInput(Map<String, Double> menuMap) {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> orderMap = new LinkedHashMap<>();
		System.out.println("Enter your order :- ");
		String order = in.nextLine();
		String[] orderArray = order.split(",");
		for (int i = 0; i < orderArray.length; i++) {
			String item =  orderArray[i].trim();
			String itemName = item.split(":")[0];
			int itemQuantity = Integer.parseInt(item.split(":")[1]);
			if (menuMap.containsKey(itemName)) {
				if (orderMap.containsKey(itemName)) {
					orderMap.put(itemName, orderMap.get(itemName) + itemQuantity);
				}
				else {
					orderMap.put(itemName, itemQuantity);
				}
			}
			else {
				System.out.println("Err : The item " + itemName + " is invalid. Please try again");
			}
		}
		return orderMap;
	}

	public static Map<String, Double> getMenu() {
		Map<String, Double> menu = new LinkedHashMap<>();
		menu.put("coffee", 80.0);
		menu.put("espresso", 120.0);
		menu.put("cappuccino", 160.0);
		menu.put("latte", 145.0);
		return menu;
	}

	public static void printBill(Map<String, Double> menuMap, Map<String, Integer> orderMap) {
		System.out.println("-------------------------------------------\n" +
							"ITEM\t\t\tQTY\t\tRATE\tAMOUNT(INR)\n" +
							"-------------------------------------------");
		Double subTotal = 0.0;
		for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
			Double rate = menuMap.get(entry.getKey());
			Double amount = rate * entry.getValue();
			subTotal += amount;
			System.out.println(entry.getKey() + "\t\t\t" + entry.getValue() + "\t\t" + rate + "\t" + amount);
		}
		System.out.println("-------------------------------------------");
		System.out.println("\t\t\t\t\tSub Total : " + subTotal);
		Double taxes = calculateTaxes(subTotal);
		Double grandTotal = subTotal + taxes;
		System.out.println("-------------------------------------------");
		System.out.println("\t\t\t\t\tGrand Total : " + grandTotal);
		System.out.println("-------------------------------------------");
	}

	public static Double calculateTaxes(Double subTotal) {
		Double sgst = (AppConstants.SGST_VALUE / 100) * subTotal;
		Double cgst = (AppConstants.CGST_VALUE / 100) * subTotal;
		Double taxes = sgst + cgst;
		System.out.println("\t\t\t\t\tSGST @ " + AppConstants.SGST_VALUE + "% : " + sgst);
		System.out.println("\t\t\t\t\tCGST @ " + AppConstants.CGST_VALUE + "% : " + cgst);
		return taxes;
	}

	public static String getCustomerName() {
		String customerName = "";
		System.out.println("Please enter your name : ");
		Scanner in = new Scanner(System.in);
		if (in.hasNext()) {
			customerName = in.nextLine().trim();
		}
		return customerName;
	}

	public static void printWelcomeMessage(String customerName) {
		System.out.println("Hi " + customerName + ", welcome to The Virtual Coffee Shop");
	}

	/*public static void printMenu() {
		System.out.println("\t\t-------------------------------\n" +
				"\t\t\t\t\tM E N U\n" +
				"\t\t-------------------------------\n" +
				"\t\t1) Eye-Opener Espresso   \t 98\n" +
				"\t\t2) Classic Cappuccino   \t110\n" +
				"\t\t3) Filter Coffee \t\t \t110\n" +
				"\t\t4) Cafe Latte \t\t\t \t115\n" +
				"\t\t5) Inverted Cappuccino  \t139\n" +
				"\t\t6) Toffee Cappuccino \t \t133\n" +
				"\t\t7) Vanilla Cappuccino   \t133\n" +
				"\t\t8) Toffee Latte \t\t \t144\n" +
				"\t\t9) Vanilla Latte \t\t \t144\n" +
				"\t\t-------------------------------\n");
	}*/

}
