package com.webspringmvc.entity;

import java.util.HashMap;
import java.util.Map;

public class DetailRoom {
	public static Map<String, Integer> avaiRoom;
	public static Map<String, Integer> discount;

	public DetailRoom() {
		avaiRoom = new HashMap<String, Integer>();
		discount = new HashMap<String, Integer>();
	}
	
	public static Map<String, Integer> getAvaiRoom() {
		return avaiRoom;
	}

	public static Map<String, Integer> getDiscount() {
		return discount;
	}

}
