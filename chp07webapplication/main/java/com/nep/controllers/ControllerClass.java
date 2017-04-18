package com.nep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nep.data.ProductDAO;
import com.nep.data.ProductRepository;
import com.nep.models.Product;

@Controller
public class ControllerClass {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value="/")
	public String index(ModelMap modelMap) {
		List<Product> products = (List<Product>) productDAO.findAll();
//		List<Product> products = productRepository.getAllProducts();
		modelMap.put("products", products);
		return "index";
	}
	
	@RequestMapping(value="/product/{id}")
	public String getProduct(@PathVariable String id, ModelMap modelMap) {
		try {
			Long idInLong = Long.parseLong(id);
			Product product = productDAO.findOne(idInLong);
			if(product == null) {
				
			} else {
				modelMap.put("product", product);
			}
		} catch (NumberFormatException e) {
			
		}
		return "product";
	}
//	@RequestMapping(value="/product/{name}")
//	public String getProduct(@PathVariable String name, ModelMap modelMap) {
//		Product product = productRepository.findByName(name);
//		modelMap.put("product", product);
//		return "product";
//	}
	
	@RequestMapping(value="/search")
	public String searchProduct(@RequestParam(required=false) String productName, ModelMap modelMap) {
		List<Product> products;
		if(productName == null) {
			products = (List<Product>) productDAO.findAll();
//			products = productRepository.getAllProducts();
		} else {
			products = productDAO.findByNameLike("%" + productName + "%");
//			products = productDAO.findByName(productName);
//			products = productRepository.findProductsWithName(productName);
		}
		modelMap.put("products", products);
		return "search";
	}
	
	@RequestMapping(value="/addproduct")
	public String addProduct(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String price,
			@RequestParam(required=false) String file,
			@RequestParam(required=false) String instock,
			ModelMap modelMap
			) {
		boolean addSuccessful;
		boolean addFailed;
		if(name==null && price==null && file==null && instock==null) {
			addSuccessful = false;
			addFailed = false;
		} else {
			if(name==null || price==null || file==null || instock==null) {
				addSuccessful = false;
				addFailed = true;
			} else {
				try {
					double priceInDouble = Double.parseDouble(price);
					boolean instockInBoolean = Boolean.parseBoolean(instock);
					Product addProduct = new Product(name, priceInDouble, 
							file, instockInBoolean);
					productDAO.save(addProduct);
					addSuccessful = true;
					addFailed = false;
				} catch (NumberFormatException e) {
					addSuccessful = false;
					addFailed = true;
				}
			}
		}
		modelMap.put("successful", addSuccessful);
		modelMap.put("failed", addFailed);
		return "addproduct";
	}
	
	@RequestMapping(value="/deleteproduct")
	public String deleteProduct(
			@RequestParam(required=false) String id,
			ModelMap modelMap
			) {
		boolean deleteSuccessful;
		boolean deleteFailed;
		if(id == null) {
			deleteSuccessful = false;
			deleteFailed = false;
		} else {
			try {
				long idInLong = Long.parseLong(id);
				if(productDAO.exists(idInLong)) {
					productDAO.delete(idInLong);
					deleteSuccessful = true;
					deleteFailed = false;
				} else {
					deleteSuccessful = false;
					deleteFailed = true;
				}
			} catch (NumberFormatException e) {
				deleteSuccessful = false;
				deleteFailed = true;
			}
		}
		modelMap.put("successful", deleteSuccessful);
		modelMap.put("failed", deleteFailed);
		return "deleteproduct";
	}
	
	@RequestMapping(value="/findproductedit")
	public String findProductEdit(
			@RequestParam(required=false) String id
			) {
		if(id == null) {
			return "findproduct";
		} else {
			try {
				Long idInLong = Long.parseLong(id);
				Product product = productDAO.findOne(idInLong);
				if(product == null) {
					return "findproduct";
				} else {
					return "redirect:/updateproduct/" + idInLong;
				}
			} catch (NumberFormatException e) {
				return "findproduct";
			}
		}
	}
	
	@RequestMapping(value="/updateproduct/{id}")
	public String updateProduct(
			@PathVariable String id,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String price,
			@RequestParam(required=false) String file,
			@RequestParam(required=false) String instock,
			ModelMap modelMap
			) {
		try {
			long idInLong = Long.parseLong(id);
			Product product = productDAO.findOne(idInLong);
			if(product == null) {
				return "redirect:/findproductedit";
			} else {
				if(name==null && price==null 
						&& file==null && instock==null) {
					
				} else {
					if(name==null || price==null 
							|| file==null || instock==null) {
						
					} else {
						try {
							double priceInDouble = Double.parseDouble(price);
							boolean instockInBoolean = Boolean.parseBoolean(instock);
							
							product.setName(name);
							product.setPrice(priceInDouble);
							product.setPicFile(file);
							product.setInStock(instockInBoolean);
							productDAO.save(product);
							
						} catch (NumberFormatException e) {
							
						}
					}
				}
				modelMap.put("product", product);
				return "updateproduct";
			}
		} catch (NumberFormatException e) {
			return "redirect:/findproductedit";
		}
	}
	
}










