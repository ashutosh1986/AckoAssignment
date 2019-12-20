package org.acko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testhelper.TestHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.acko.base.TestBase;
import org.acko.client.RestClient;
import org.acko.utils.Utils;


public class GetAPITest extends TestBase{
	String serviceUrl;
	String apiUrl;
	String url;
	TestBase testBase;

	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;
	TestHelper testHelper = new  TestHelper();

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
	}

	@Test(priority=1)
	public void getAPITest() throws ClientProtocolException, IOException{
		ArrayList<String>files;
		files = Utils.listFilesInDirectory();

		BufferedReader enBr = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/java/org/acko/resources/"+files.get(0)+""));
		BufferedReader frBr = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/java/org/acko/resources/"+files.get(1)+""));

		try {
			while (true) {
				String fileAUrl = enBr.readLine();
				String fileBUrl = frBr.readLine();

				if (fileAUrl == null || fileBUrl == null)
					break;
				else {
					closebaleHttpResponse = restClient.get(fileAUrl);
					int statusCode1 = closebaleHttpResponse.getStatusLine().getStatusCode();
					Assert.assertEquals(statusCode1, RESPONSE_STATUS_CODE_200, "Status code is not 200");

					String responseString1 = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

					closebaleHttpResponse = restClient.get(fileBUrl);
					int statusCode2 = closebaleHttpResponse.getStatusLine().getStatusCode();
					Assert.assertEquals(statusCode2, RESPONSE_STATUS_CODE_200, "Status code is not 200");

					String responseString2 = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

					testHelper.comparator(fileAUrl, fileBUrl, responseString1, responseString2);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}

