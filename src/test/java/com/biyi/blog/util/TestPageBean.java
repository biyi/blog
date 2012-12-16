package com.biyi.blog.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPageBean {
	
	@Before
	public void before(){}
	
	@After
	public void after(){}
	
	@Test
	public void test1(){
		try {
			PageBean pageBean = new PageBean();
			
			pageBean.setCurrentPage(1);
			pageBean.setPageLength(20);
			pageBean.setTotalResult(50);
			
			Assert.assertEquals(0, pageBean.getStartIndex());
			Assert.assertEquals(3, pageBean.getTotalPage());
			
			pageBean.setCurrentPage(2);
			
			Assert.assertEquals(20, pageBean.getStartIndex());
			Assert.assertEquals(3, pageBean.getTotalPage());
			
			pageBean.setCurrentPage(3);
			
			Assert.assertEquals(40, pageBean.getStartIndex());
			Assert.assertEquals(3, pageBean.getTotalPage());
			
			pageBean.setCurrentPage(4);
			
			Assert.assertEquals(40, pageBean.getStartIndex());
			Assert.assertEquals(3, pageBean.getTotalPage());
			Assert.assertEquals(3, pageBean.getCurrentPage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
