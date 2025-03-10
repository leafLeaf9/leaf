package com.gousade.mapper;

import com.gousade.entity.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    // 自定义查询方法（按名称搜索）
    List<Product> findByName(String name);

    // 使用@Query注解自定义DSL查询
    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    List<Product> searchByName(String name);
}
