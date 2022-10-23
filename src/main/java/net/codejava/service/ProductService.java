package net.codejava.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import net.codejava.repository.ProductRepository;
import net.codejava.entity.Product;
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
