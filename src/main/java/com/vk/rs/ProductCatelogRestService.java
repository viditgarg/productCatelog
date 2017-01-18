package com.vk.rs;

import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.vk.model.Category;
import com.vk.model.Product;
import com.vk.service.ProductCatelogService;
import org.springframework.beans.factory.annotation.Autowired;



@Path( "/products" ) 
public class ProductCatelogRestService {
	
	@Autowired
	private ProductCatelogService productCatelogService;
	
	private List<Product> products;
	private List<Category> categories;
	
	private Product product;
	private Category category;
	
	private double totalCost;
	private double totalTax;
	
	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	

	
	@Produces( { "application/json" } )
	@GET
	public JsonObject getItemizedBill() { 		
		
	//	JsonArray json = null; 	// =  (JsonArray) Json.createArrayBuilder();
		JsonArrayBuilder kvArrBld = Json.createArrayBuilder();
		JsonObject jsonBuilder = null;
		
		products = productCatelogService.findAllProducts();
		categories = productCatelogService.findAllCategories();
		
		Iterator<Product> it = products.iterator();
		while (it.hasNext()){
			product = it.next();
			this.totalCost += product.getCost();
			this.totalTax += product.getSalesTax();
			
			jsonBuilder = Json.createObjectBuilder().add("Product Name:", product.getName()).add("product category:", product.getProductCategory())
					.add("Sales Category:", product.getCategoryType())
					.add("item cost:", (product.getCost()+" Rs.")).add("sales tax:", (product.getSalesTax()+" Rs.")).build();
			//jsonBuilder.add
			kvArrBld.add(jsonBuilder);			
		}
		
		JsonObjectBuilder json = Json.createObjectBuilder();
		json.add("Products:", kvArrBld).add("products costs:", (this.totalCost+" Rs."))
		.add("products taxes:", (this.totalTax+" Rs."))
		.add("Total Cost::", (this.totalCost+this.totalTax+ " Rs."));
		return json.build();
	}
	
	
	
}
