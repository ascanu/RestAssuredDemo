package com.Buy.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.qa.tests.CommentTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class BuyRelatedApiTest {
	public static Logger logger;
	//@Test
	public void test01_Get() {
		Response response = RestAssured.when().get("http://localhost:3030/products");

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().jsonPath().prettify());
		JsonPath json = response.jsonPath();

		
		String id = response.jsonPath().getString("data[0].id");
		System.out.println("id is "+id);
		
		
		 

	}
	
	String created_id;
	@Test
	public void test01_Post() {
		
		ProductIdPost post_comment = new ProductIdPost("apple","hari",32,322,"234","2342","Manufactore","V1","http://www.google.com","testimg");



Response response = (Response) RestAssured.
	        given()	        
	           // .header("Accept",ContentType.JSON.getAcceptHeader() )
	            .contentType("application/json")
	            .body(post_comment)
	            .post("http://localhost:3030/products");	         
	           System.out.println( response.getStatusCode());
	           
	           Assert.assertEquals(201,response.getStatusCode());
	           System.out.println(response.getBody().jsonPath().prettify());
	   		JsonPath json = response.jsonPath();

	   		
	   		created_id = response.jsonPath().getString("id");
	   		System.out.println("id is "+created_id);
	   		
	    }

	
	
	

	
	}
		
		 

	

