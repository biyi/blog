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
	public void test_isNull(){
		try {
			Assert.assertTrue(StringUtil.isNull(null));
			Assert.assertTrue(StringUtil.isNull(""));
			Assert.assertTrue(StringUtil.isNull("    "));
			Assert.assertTrue(StringUtil.isNull("null"));
			Assert.assertTrue(StringUtil.isNull("null "));
			
			Assert.assertFalse(StringUtil.isNull("null1 "));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
