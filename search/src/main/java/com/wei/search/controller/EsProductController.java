package com.wei.search.controller;

import com.wei.search.module.EsProduct;
import com.wei.search.repository.ProductRepository;
import com.wei.search.service.EsProductService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @Author: weizz
 * @Date: 2019/5/27 16:16
 * @Description:
 * @Version:1.0
 */
@RestController
public class EsProductController {

    @Autowired
    private EsProductService esProductService;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/save")
    public String save(){
        EsProduct esProduct = new EsProduct();
        esProduct.setId(1L);
        esProduct.setTitle("华为手机");
        esProduct.setContent("最大内存128G");
        esProduct.setImgUrl("www.jd.com");
        esProductService.save(esProduct);
        return "添加成功";
    }
    @GetMapping("delete")
    public String delete(long id){
        productRepository.deleteById(id);
        return "删除成功";
    }
    @GetMapping("getOne")
    public EsProduct getOne(long id){
        Optional<EsProduct> optional = productRepository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        return optional.get();
    }

    @GetMapping("update")
    public String update(){
        EsProduct esProduct = getOne(1L);
        esProduct.setTitle("小米手机");
        productRepository.save(esProduct);
        return "更新成功";
    }
    @RequestMapping("/getList")
    public List<EsProduct> getList(String content){

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title",content);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        System.out.println("查询语句:"+searchQuery.getQuery().toString());
        Page<EsProduct> search = productRepository.search(searchQuery);
        return search.getContent();
    }
}
