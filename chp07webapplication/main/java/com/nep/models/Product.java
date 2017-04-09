package com.nep.models;

public class Product {

	private long id;
	private String name;
	private double price;
	private String picFile;
	private boolean inStock;
	
	public Product(String name, double price, String picFile, boolean inStock) {
		this.name = name;
		this.price = price;
		this.picFile = picFile;
		this.inStock = inStock;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicFile() {
		return picFile;
	}

	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	
	
}
