package com.poli.inventory.services;

import com.poli.inventory.domain.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getArticle();
    Article createArticle(Article article) throws Exception;
}
