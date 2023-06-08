package com.springboot.service.impl;

import com.springboot.dto.ProductDto;
import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;
import com.springboot.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public final static String PRODUCT_PATH = "/home/shailesh/Documents/Java/Product Management System/Product-Management-System/src/main/resources/static/image/product";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Product getProduct(Integer productId) {
        return productRepository.findProduct(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer productId) {
        Product product = productRepository.findProduct(productId);
        if (product != null) {
            productRepository.delete(product);
            Path path = Paths.get(PRODUCT_PATH + File.separator + product.getImageUrl());
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product dtoToProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
