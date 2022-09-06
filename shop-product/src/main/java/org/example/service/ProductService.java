package org.example.service;

import org.example.bean.Product;

public interface ProductService {
    Product getProductById(Long pid);

    int updateProductStockById(Integer count,Long id);
}
