package com.Buy.tests;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;


public class ProductIdPost implements IAnnotationTransformer {
	
	String name;
	String type;
	int price;
	int shipping;
	String upc;
	String description;
	String manufacturer;
	String model;
	String url;
	String image;
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getShipping() {
		return shipping;
	}



	public void setShipping(int shipping) {
		this.shipping = shipping;
	}



	public String getUpc() {
		return upc;
	}



	public void setUpc(String upc) {
		this.upc = upc;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getManufacturer() {
		return manufacturer;
	}



	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	ProductIdPost(String name,String type,int price,int shipping,String upc,String description,String manufacturer,String model,String url,String image){
		this.name =name ;
		this.type= type;
		this.price= price;
		this.shipping=shipping;
		this.upc=upc;
		this.description=description;
		this.manufacturer=manufacturer;
		this.model=model;
		this.url=url;
		this.image=image;	
	}
	
	

				@Override
		public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
			// TODO Auto-generated method stub
			
		}	
	

}
