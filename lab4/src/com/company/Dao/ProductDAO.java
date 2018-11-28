package com.company.Dao;

import com.company.Entities.Product;

import java.util.ArrayList;

public interface ProductDAO {
    void createProduct(String name);
    ArrayList<Product> getProducts();
}
