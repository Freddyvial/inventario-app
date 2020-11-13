package com.poli.inventory.repositories;

import com.poli.inventory.domain.Article;

import java.util.List;

public interface ArticleRepository {

    List<Article> getArticles();
    Article createArticle(Article article);
    Article checkArticle(String serial);
    Article update(Article article);

}