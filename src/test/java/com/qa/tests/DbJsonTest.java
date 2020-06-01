package com.qa.tests;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.tests.utility.TestUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DbJsonTest {
	int comment_id;
	static String sheetName = "Sheet1";
	static String getDeleteExcelData = "Sheet2";
	

	@DataProvider
	public Object[][] getExcelData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		
		return data;
	}
	@DataProvider
	public Object[][] getDeleteExcelData(){
		Object data[][] = TestUtil.getTestData(getDeleteExcelData);
		return data;
	}
	public static void main(String[] args) {
		Object data[][]  = TestUtil.getTestData(sheetName)	;
		
	}
	
    @Test
	public void test01GetComment() throws Exception {
    	Jsoup.connect("https://google.com/").validateTLSCertificates(false).get();

		Response res = RestAssured.when().get("http://localhost:3000/comments");
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().asString());
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(dataProvider = "getExcelData")
	public void test02Post_Comment(String id,String post,String comment) throws Exception {
		CommentTest post_comment = new CommentTest(Integer.parseInt(id),post,comment);
		Response resp = RestAssured
				.given()
				.contentType("application/json")
				.body(post_comment)
				.when()
				.post("http://localhost:3000/comments");

		Assert.assertEquals(resp.getStatusCode(), 201);
		JsonPath json = resp.jsonPath();
		comment_id = json.get("id");
		System.out.println("comment id is -->" + comment_id);
		Thread.sleep(1000);
		
	}


	@Test(dataProvider = "getDeleteExcelData")
	public void test03_Delete_Comment(String id) throws Exception {
		Response resp = RestAssured
				.given()
				.contentType("application/json")
				.when()
				.delete("http://localhost:3000/comments/"+Integer.parseInt(id));
		Thread.sleep(1000);
		Assert.assertEquals(resp.getStatusCode(), 200);		
	}
	
	@Test(dependsOnMethods="test03_Delete_Comment")	
	public void test04_Post() throws Exception {
		CommentTest post_Post = new CommentTest(Util.getRandomNumberInRange(1, 100), "title", "author");
		Response resp = RestAssured
				.given()
				.contentType("application/json")
				.body(post_Post)
				.when()
				.post("http://localhost:3000/posts");

		Assert.assertEquals(resp.getStatusCode(), 201);
		JsonPath json = resp.jsonPath();
		comment_id = json.get("id");
		System.out.println("comment id is -->" + comment_id);
		Thread.sleep(2000);
		
	}

	@Test(dependsOnMethods="test04_Post")
		public void test04_Put_post() throws Exception {
			CommentTest put_Post = new CommentTest(comment_id, "title1","author");
			Response resp = RestAssured
					.given()
					.contentType("application/json")
					.body(put_Post)
					.when()
					.put("http://localhost:3000/posts/"+comment_id);

			Assert.assertEquals(resp.getStatusCode(), 200);
			JsonPath json = resp.jsonPath();
			comment_id = json.get("id");
			System.out.println("comment id is -->" + comment_id);
			Thread.sleep(2000);
			
		}

	@Test(dependsOnMethods="test04_Put_post")
	
		public void test04_Delete_Post() {
		Response resp = RestAssured
				.given()
				.contentType("application/json")
				.when()
			.delete("http://localhost:3000/posts/"+comment_id);
		
		Assert.assertEquals(resp.getStatusCode(), 200);		
	}
	
		
	
	public static void main2(String[] args) {
		// devide programe
		System.out.println("Devide numbers are");
		for (int i = 0; i < 10; i++) {
			Util.generateRandomChars("123456789", 5);
			String a = Util.generateRandomChars("123456789", 6);
			int num1 = Integer.parseInt(a);

			int num2 = Util.getRandomNumberInRange(11, 18);
			System.out.println(num1 * num2 + "      " + num2);
		}
	}

//multiple  programe
	public static void main1(String[] args) {
		System.out.println("Multiple number are");
		for (int i = 0; i < 10; i++) {
			System.out.println(
					Util.generateRandomChars("123456789", 6) + "   " + Util.generateRandomChars("123456789", 5));
		}

	}

	public static void main3(String[] args) {
		System.out.print("Enter the String");
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		String st1 = st.replaceAll("\\s+", "");
		int[] ar = new int[256];
		for (int i = 0; i < st1.length(); i++) {
			ar[st1.charAt(i)] = ar[st1.charAt(i)] + 1;
		}
		for (int i = 0; i < 256; i++) {
			char ch = (char) i;
			if (ar[i] > 0) {
				if (ar[i] != 1) {
					System.out.println(ch + " " + ar[i]);
				}
			}
		}

	}

}