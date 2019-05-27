package com.wei.search.service;

import com.wei.search.module.EsProduct;
import com.wei.search.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: weizz
 * @Date: 2019/5/27 16:14
 * @Description:
 * @Version:1.0
 */
@Service
public class EsProductServiceImpl implements EsProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(EsProduct esProduct) {
        productRepository.save(esProduct);
    }
}
