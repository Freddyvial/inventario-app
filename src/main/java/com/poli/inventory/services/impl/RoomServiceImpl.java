package com.poli.inventory.services.impl;

import com.poli.inventory.domain.Article;
import com.poli.inventory.repositories.ArticleRepository;
import com.poli.inventory.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticle() {
        return articleRepository.getArticles();
    }

    @Override
    public Article createArticle(Article article) throws Exception {
        Article articleExist = articleRepository.checkArticle(article.getSerial());

        if(articleExist == null) {
            return articleRepository.createArticle(article);
        } else {
            return  null;

        }
    }
}
