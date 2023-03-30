package com.ditheringllama.demo.utils;

import org.jgroups.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeneralUtils {
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	//obj is a List
	public static String convertListToJsonObject(Object obj) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String JSONObject = gson.toJson(obj);
		return new String(JSONObject);
	}
}
