package com.vk.service;

import java.util.List;

import com.vk.model.Category;
import com.vk.model.Product;

public interface ProductCatelogService {
	
	Product findById(long id);
    
	Product findByName(String name);
	
	int findProductType(Product product);
     
    void saveProduct(Product product);
     
    void updateProduct(Product product);
     
    void deleteProductById(long id);
 
    List<Product> findAllProducts(); 
    
    List<Category> findAllCategories();
    
    String findProductCategory(int id);
    
    float findLevyPercentage(int id);
    
    Category findById(int id);
    
    void deleteAllProducts();
     
    public boolean isProductExist(Product product);

}
