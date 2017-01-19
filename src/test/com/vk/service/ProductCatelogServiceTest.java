package com.vk.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.vk.model.Product;

public class ProductCatelogServiceTest {
	
private List<Product> products;
	
	@Test
	public void testFindAllProducts(){
		
		ProductCatelogService obj = new ProductCatelogServiceImpl();
		
		products = obj.findAllProducts();
		
		
		
		Assert.assertTrue(products != null);
	}

}
