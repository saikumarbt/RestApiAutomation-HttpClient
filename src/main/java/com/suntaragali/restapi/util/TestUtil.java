package com.suntaragali.restapi.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String getValueByJpath(JSONObject responsejson, String jpath) {
		Object obj = responsejson;
		for(String s: jpath.split("/")) {
			System.out.println("Jpath Split:"+ s);
			if(!s.isEmpty()) {
				if(!(s.contains("[")||s.contains("]")))
				{
					obj=((JSONObject)obj).get(s);
				}
				else if(s.contains("[")||s.contains("]")) {
					obj=((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]","")));
				}
				
			}
		}
		return obj.toString();
	}
	
}
