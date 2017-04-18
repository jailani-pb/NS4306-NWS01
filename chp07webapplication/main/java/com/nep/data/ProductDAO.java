package com.nep.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.nep.models.Product;

@Transactional
public interface ProductDAO extends CrudRepository<Product, Long> {
	
	public List<Product> findByName(String name);
	public List<Product> findByNameLike(String name);
	
}







