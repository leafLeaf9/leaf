package com.gousade.controller;

import com.gousade.entity.Article;
import com.gousade.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public Article save(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    @GetMapping("/{id}")
    public Optional<Article> getById(@PathVariable String id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/search")
    public List<Article> search(@RequestParam String keyword) {
        return articleService.searchByTitle(keyword);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        articleService.deleteArticle(id);
    }
}