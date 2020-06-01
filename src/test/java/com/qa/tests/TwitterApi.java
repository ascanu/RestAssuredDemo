package com.qa.tests;

import java.util.ArrayList;
import java.util.Map;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.tests.utility.TestBase;
import com.qa.tests.utility.TestUtil;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.TweetPayload;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class TwitterApi extends TestBase {
	String ConsumerKey = "Test";
	String ConsumerSecret = "rquQ1ly44IGbY7ZUvjdFOmpkzo0zWXawTq7537tVh6fnXpsLVs";
	String Token = "592532246-P3h3S3kTmrt3tUqCgekvUzr3zsbG7TzAk5YH61sz";
	String TokenSecret = "cCC8MXIQwDXIlGhtKrPFkvs5ZXoF6bTiEQMSBTp1Lztsa";
	String tweet_Id, id1;

	@BeforeMethod
	public void setUp() {
		TestBase.init();
		RestAssured.baseURI = prop.getProperty("serviceurl");
		System.out.println(RestAssured.baseURI);
	}

	 @Test
	public void posttweet() throws Exception {
		Response resp = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				//.body(TweetPayload.TweetProjectCreationPayload())
				.post("/statuses/update.json?status="
						+ Util.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ", 40));
		
		resp.getStatusCode();
		System.out.println("post tweet status is" + resp.getStatusCode());
		System.out.println(resp.getBody().jsonPath().prettify());
		JsonPath json = resp.jsonPath();
		tweet_Id = json.get("id_str");
		System.out.println("Tweet id is -->" + tweet_Id);
		Assert.assertEquals(200, resp.getStatusCode());
	}

	 @Test(dependsOnMethods = { "posttweet" })
	public void showTweetId() throws Exception {
		Thread.sleep(10000);
		Response resp = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.get("/statuses/show.json?id=" + tweet_Id);
		resp.getStatusCode();
		JsonPath json = resp.jsonPath();
		tweet_Id = json.get("id_str");
		System.out.println(resp.getBody().jsonPath().prettify());
		System.out.println("status code is" + resp.getStatusCode());
		System.out.println("Tweet id is getting as-->" + tweet_Id);
		Assert.assertEquals(200, resp.getStatusCode());
	}

	 @Test(dependsOnMethods = { "showTweetId" })
	public void deleteTweet() {
		Response resp = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.post("/statuses/destroy/:id.json?id=" + tweet_Id);
		resp.getStatusCode();
		System.out.println(resp.getBody().jsonPath().prettify());
		System.out.println("Tweet is deleted " + resp.getStatusCode());
		Assert.assertEquals(200, resp.getStatusCode());
	}

	 @Test()
	public void FavTweet() {
		Response resp = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.get("/favorites/list.json?count=10");
		resp.getStatusCode();
		System.out.println("FavTweet" + resp.getBody().jsonPath().prettify());
		Assert.assertEquals(200, resp.getStatusCode());

	}

	 @Test()
	public void StatusesOembedTweet() {
		Response resp = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.get("https://twitter.com/Interior/status/1177564939373801472");
		resp.getStatusCode();
		System.out.println("Response code is" + resp.getStatusCode());
		System.out.println(resp.getBody().asString());
		System.out.println("StatusesOembedTweet" + resp.getBody().jsonPath().prettify());
		Assert.assertEquals(200, resp.getStatusCode());

	}

	 @Test()
	public void StatusesLookupTweet() throws Exception {
		String API = "/statuses/lookup.json?id=1177564939373801472";
		Response response = RestAssured
				.given()
				.auth()
				.oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).get(API);

		// Get Headers are
		Headers headers = response.getHeaders();
		System.out.println(headers);
		String contenttype = headers.getValue("content-type");
		System.out.println("Content type is" + contenttype);
		// Get Status code is
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: " + statusCode);
		// Get Response Body
		System.out.println(response.getBody().asString());
		System.out.println("StatusesLookupTweet" + response.getBody().jsonPath().prettify());
		String usernames = response.jsonPath().getString("id_str");
		System.out.println(usernames);
		String text = response.jsonPath().getString("created_at");
		System.out.println(text);
		Map<String, String> company = response.jsonPath().getMap("user[0]");
		System.out.println(company.get("name"));
		String usernames1 = response.jsonPath().getString("user[0]");
		System.out.println(usernames1);

	}

	 @Test()
	public void StatusesRetweets_of_meTweet() throws Exception {
		String API = "/favorites/list.json?count=20&screen_name=sanjubhansali";
		Response response = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).get(API);

		// Get Headers are
		Headers headers = response.getHeaders();
		System.out.println(headers);
		String contenttype = headers.getValue("content-type");
		System.out.println("Content type is" + contenttype);

		// Get Status code is
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: " + statusCode);

		// Get Response Body
		System.out.println(response.getBody().asString());
		System.out.println("StatusesLookupTweet" + response.getBody().jsonPath().prettify());

		String userid = response.jsonPath().getString("id_str");
		System.out.println(userid);
		String created_at = response.jsonPath().getString("created_at");
		System.out.println(created_at);

		Map<String, String> company = response.jsonPath().getMap("user[0]");
		System.out.println(company.get("name"));

		String userText = response.jsonPath().getString("text[0]");
		System.out.println(userText);

		int LengthofText = response.jsonPath().getList("$").size();
		System.out.println("Size is " + LengthofText);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		for (int i = 0; i < LengthofText; i++) {
			list.add(response.jsonPath().getString("text[" + i + "]"));
			list2.add(response.jsonPath().getString("user[" + i + "].name"));
		}
		for (int i = 0; i < list.size(); i++) {
			String str1 = list.get(i);
			System.out.println("Text value is " + str1);
			System.out.println(list2.get(i));
		}
	}

	 @Test(description = "To verify that given data belongs to the response body")
	 public void verifyDataInResponseBody1() {	 
		 Response response=RestAssured.given()
		 .when()
		 .get("http://dummy.restapiexample.com/api/v1/employees");
		 
		 System.out.println(response.getBody().asString());
			System.out.println("StatusesLookupTweet" + response.getBody().jsonPath().prettify());
			Map<String, String> company = response.jsonPath().getMap("data[0]");
			System.out.println(company.get("employee_name"));
	 }


	 
}