package com.qa.tests;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class CommentTest implements IAnnotationTransformer  {
	
	int id;
	String post;
	String comment;
	
	
	CommentTest(int id ,String post, String comment ){
		this.id=id;
		this.post=post;
		this.comment=comment;
		
	}
	 CommentTest(int id, String post) {
			this.id=id;
			this.post=post;
				}
	 
	 CommentTest(int id) {
			this.id=id;			
				}



	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
	}	
}
