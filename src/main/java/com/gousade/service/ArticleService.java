package com.gousade.service;

import com.gousade.entity.Article;
import com.gousade.mapper.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Optional<Article> getArticleById(String id) {
        return articleRepository.findById(id);
    }

    public List<Article> searchByTitle(String keyword) {
        return articleRepository.findByTitleContaining(keyword);
    }

    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }
}
