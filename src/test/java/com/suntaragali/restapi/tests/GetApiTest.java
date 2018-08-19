package com.suntaragali.restapi.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suntaragali.restapi.base.TestBase;
import com.suntaragali.restapi.client.RestClient;
import com.suntaragali.restapi.util.TestUtil;

public class GetApiTest extends TestBase{
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setUp(){
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		url = serviceUrl + apiUrl;
		
	}
	
	@Test (priority = 2)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		httpResponse = restClient.get(url);
		System.out.println("Api URL under Testing: "+ url);
		int statusCode = httpResponse.getStatusLine().getStatusCode();	
		System.out.println("Status Code -----> " + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//Response in String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	    System.out.println("Response String content:" +responseString);
		//Json Response
		
		//int i = responseString.indexOf("{");
	    if(responseString.charAt(0)=='[') {
		//responseString = responseString.substring(i);
		JSONArray responseJson = new JSONArray(responseString.toString());
		System.out.println("Response JSON from API --->" + responseJson);
	    } else {
		JSONObject responseJson = new JSONObject(responseString); 
		System.out.println("Response JSON from API --->" + responseJson);
	    }
		
		
		//Get single value from Json
		//String perPageValue = TestUtil.getValueByJpath(responseJson, "per_page");
		//System.out.println("Per_Page Value is : "+perPageValue);
		
	//	Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
	/*	//Get the value from Json array
		String lastName = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJpath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		System.out.println("Last Name Value is : "+lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);*/
		
		//Assert.assertEquals(lastName, "Bluth");
		
		// All Headers
		Header [] headerArray = httpResponse.getAllHeaders();
		HashMap <String, String> allHeaders = new HashMap<String, String>();
		for (Header header: headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Contents -->> " + allHeaders);
	}
	
	@Test (enabled = false)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content Type", "application/json");
		httpResponse = restClient.get(url, headerMap);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();	
		System.out.println("Status Code -----> " + statusCode);
		
	//	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//Response in String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		
		//Json Response
		JSONObject responseJson = new JSONObject(responseString); 
		System.out.println("Response JSON from API --->" + responseJson);
		
		//Get single value from Json
		String perPageValue = TestUtil.getValueByJpath(responseJson, "per_page");
		System.out.println("Per_Page Value is : "+perPageValue);
		
	//	Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//Get the value from Json array
		String lastName = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJpath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		System.out.println("Last Name Value is : "+lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
	//	Assert.assertEquals(lastName, "Bluth");
		
		// All Headers
		Header [] headerArray = httpResponse.getAllHeaders();
		HashMap <String, String> allHeaders = new HashMap<String, String>();
		for (Header header: headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Contents -->> " + allHeaders);
	}
	
	
}
