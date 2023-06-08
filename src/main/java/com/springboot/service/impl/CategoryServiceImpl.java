package com.springboot.service.impl;

import com.springboot.dto.CategoryDto;
import com.springboot.entity.Category;
import com.springboot.repository.CategoryRepository;
import com.springboot.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    public final static String CATEGORY_PATH = "/home/shailesh/Documents/Java/Product Management System/Product-Management-System/src/main/resources/static/image/category";

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Integer menuId) {
        return categoryRepository.findCategory(menuId);
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findCategory(categoryId);
        if (category != null) {
            categoryRepository.delete(category);
            Path path = Paths.get(CATEGORY_PATH + File.separator + category.getImageUrl());
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Category delete successfully";
        } else {
            return "Category doesn't exits";
        }
    }

    @Override
    public CategoryDto categoryToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category dtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
