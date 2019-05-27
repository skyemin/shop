package com.wei.search.repository;

import com.wei.search.module.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends ElasticsearchRepository<EsProduct,Long> {

}
