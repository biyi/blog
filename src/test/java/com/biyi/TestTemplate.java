package com.biyi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTemplate {
	
	@Before
	public void before(){}
	
	@After
	public void after(){}
	
	@Test
	public void test1(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
