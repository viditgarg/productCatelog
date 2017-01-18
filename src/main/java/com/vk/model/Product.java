package com.vk.model;

public class Product {
	
	private long id;
	private String name;    
    private int categoryTypeId;
    private String categoryType;
    private double cost;
    private double salesTax;
    private String productCategory;
    
    
    
    public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}



	
    
 
    public Product(){
        id=0;
    }
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
     
    public Product(long id, String name, int categoryTypeId, double cost, String productCategory){
        this.id = id;
        this.name = name;
        this.categoryTypeId = categoryTypeId;
        this.cost = cost;
        this.productCategory = productCategory;
    }

}
