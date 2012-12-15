package com.biyi.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStringUtil {
	
	@Before
	public void before(){}
	
	@After
	public void after(){}
	
	@Test
	public void test_isEmpty(){
		try {
			Assert.assertTrue(StringUtil.isEmpty(null));
			Assert.assertTrue(StringUtil.isEmpty(""));
			Assert.assertTrue(StringUtil.isEmpty("    "));
			Assert.assertTrue(StringUtil.isEmpty("null"));
			Assert.assertTrue(StringUtil.isEmpty("null "));
			
			Assert.assertFalse(StringUtil.isEmpty("null1 "));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
