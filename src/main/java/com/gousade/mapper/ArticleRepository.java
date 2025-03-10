package com.gousade.mapper;

import com.gousade.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    // 自定义查询
    List<Article> findByTitleContaining(String keyword);
}