/**
 * 
 */
package org.testhelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.acko.base.TestBase;
import org.acko.client.RestClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author ashutosh.gupta
 *
 */
public class TestHelper extends TestBase{

	CloseableHttpResponse closebaleHttpResponse;
	RestClient restClient;

	public void comparator(String fileAUrl, String fileBUrl, String responseString1,
			String responseString2) {
		JSONObject responseJson1 = new JSONObject(responseString1);
		JSONObject responseJson2 = new JSONObject(responseString2);
		SoftAssert softAssert = new  SoftAssert();
		JsonParser parser = new JsonParser();
		JsonElement o1 = parser.parse(responseString1);
		JsonElement o2 = parser.parse(responseString2);
		if(o1.equals(o2)) {
			softAssert.assertEquals(o1, o2, ""+fileAUrl +" is EQUALS "  + fileBUrl+"");
			System.out.println(fileAUrl +" is EQUALS "  + fileBUrl);
		}else {
			softAssert.assertEquals(o1, o2, ""+fileAUrl +" is NOT EQUALS "  + fileBUrl+"");
			System.out.println(fileAUrl +" is NOT  EQUALS "  + fileBUrl);
		}
	}

}
