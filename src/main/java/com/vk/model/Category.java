package com.vk.model;

public class Category {
	
	private int id;
	private String name;
	private float levyPercentage;
	
	public Category(){
		
	}

	public Category(int id, String name, float levyPercentage){
		this.id = id;
		this.name = name;
		this.levyPercentage = levyPercentage;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getLevyPercentage() {
		return levyPercentage;
	}

	public void setLevyPercentage(float levyPercentage) {
		this.levyPercentage = levyPercentage;
	}
		
}
