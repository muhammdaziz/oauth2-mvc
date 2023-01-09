package com.company.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import com.company.repository.ProductRepository;
import com.company.entity.Product;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	public List<Product> listAll() {
		return productRepository.findAll();
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public Product get(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
	}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
