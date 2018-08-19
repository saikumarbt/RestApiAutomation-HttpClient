package com.suntaragali.restapi.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntaragali.restapi.base.TestBase;
import com.suntaragali.restapi.client.RestClient;
import com.suntaragali.restapi.data.Users;

public class PostAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		url = serviceUrl + apiUrl;
	}

	@Test

	public void postApiTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content Type", "application/json");

		// Jackson API to do marshalling Java Object to Json
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("morpheus", "leader");

		// Object to JSON
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/suntaragali/restapi/data/users.json"),
				user);

		// Object to JSON in String
		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);

		httpResponse = restClient.post(url, userJsonString, headerMap);

		System.out.println("Api URL under Testing: " + url);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code -----> " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201, "Status code is not 200");

		// Response in String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		System.out.println("Response String content:" + responseString);

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API :" + responseJson);

		// Json to Java Object
		Users usersObj = mapper.readValue(responseString, Users.class);
		System.out.println(user.getName().equals(usersObj.getName()));
		System.out.println(user.getJob().equals(usersObj.getJob()));
		System.out.println(usersObj.getId());
		System.out.println(usersObj.getCreatedAt());
	}

}
