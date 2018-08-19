package com.suntaragali.restapi.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	/**
	 * Create a GET method without Headers
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
			CloseableHttpClient httpClient = HttpClients.createDefault(); //Create a default connection
			HttpGet httpGet = new HttpGet(url); //Http Get request
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// hit the request url
			return httpResponse;			
			/*//Status code
			int statusCode = httpResponse.getStatusLine().getStatusCode();	
			System.out.println("Status Code -----> " + statusCode);
			
			//Response in String
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			
			//Json Response
			JSONObject responseJson = new JSONObject(responseString); 
			System.out.println("Response JSON from API --->" + responseJson);
			
			// All Headers
			Header [] headerArray = httpResponse.getAllHeaders();
			HashMap <String, String> allHeaders = new HashMap<String, String>();
			for (Header header: headerArray) {
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println("Header Contents -->> " + allHeaders);*/
	}
	
	/**
	 * Create a GET method Headers (Method overloading)
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
			CloseableHttpClient httpClient = HttpClients.createDefault(); //Create a default connection
			HttpGet httpGet = new HttpGet(url); //Http Get request
			for(Map.Entry<String, String> entry: headerMap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}			
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// hit the request url
			return httpResponse;		
	}
	
	/**
	 * Create a POST Method
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	
	public CloseableHttpResponse post(String url, String payLoadEntity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); //Create a default connection
		HttpPost httpPost = new HttpPost(url);// POST request
		httpPost.setEntity(new StringEntity(payLoadEntity)); //Define the payload
		//for Headers
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}	
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}
}
