package com.nep.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nep.models.Product;

@Component
public class ProductRepository {

	private static final List<Product> ALL_PRODUCTS = Arrays.asList(
			new Product("HTC One x9", 500.99, "htconex9.png", false),
			new Product("Apple iPhone 6s", 1210.50, "iphone6s.png", true),
			new Product("Samsung Galaxy S7 Edge", 885.95, "samsungs7edge.png", true),
			new Product("Apple iPad Pro", 1322.97, "ipadpro.png", true),
			new Product("Samsung Galaxy Note 7", 880.88, "samsungnote7.png", false),
			new Product("Oppo F1s", 400.28, "oppof1s.png", true)
			); 
	
	public List<Product> getAllProducts() {
		return ALL_PRODUCTS;
	}
	
	public Product findByName(String name) {
		for(Product product : ALL_PRODUCTS) {
			if(product.getName().equalsIgnoreCase(name)) {
				return product;
			}
		}
		return null;
	}
	
	public List<Product> findProductsWithName(String name) {
		List<Product> products = new ArrayList<Product>();
		for(Product product : ALL_PRODUCTS) {
			if(product.getName().toLowerCase().contains(name.toLowerCase())) {
				products.add(product);
			}
		}
		return products;
	}
	
}









