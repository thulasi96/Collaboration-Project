package com.coll.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class dbconfigtest {

	@Before
	public void First()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
	}
	@Test
	public void test() {
		System.out.print("*****DATABASE CONNECTED*****");
		
	}

}
