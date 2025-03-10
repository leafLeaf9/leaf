package com.gousade.service;

import com.gousade.entity.Product;
import com.gousade.mapper.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchByName(keyword);
    }
}
