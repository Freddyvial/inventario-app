package com.poli.inventory.services.impl;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.TypeArticle;
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
    public List<Article> getArticle(String idCampus) {
        return articleRepository.getArticles(idCampus);
    }

    @Override
    public List<TypeArticle> getTypeArticle() {
        return articleRepository.getTypeArticle();
    }

    @Override
    public List<Article> consulArticleByRoom(Integer idRoom) {
        return articleRepository.consulArticleByRoom(idRoom);
    }

    @Override
    public Article changeStateArticle(Article article) {
        return articleRepository.changeStateArticle(article);
    }

    @Override
    public Article changeIdRoomArticle(Article article) {
        return articleRepository.changeIdRoomArticle(article);
    }

    @Override
    public Article createArticle(Article article) throws Exception {
        if (article.getId() == 0) {
            return articleRepository.createArticle(article);
        } else {
            Article articleExist = articleRepository.checkArticle(article.getSerial());
            if (articleExist != null) {
                return articleRepository.createArticle(article);
            } else {
                return null;

            }
        }


    }
    @Override
    public  List<Article> articlesForChanges(List<TypeArticle> typeArticleList, String idCampus){
        List<Article> articles = null;
        String idRoom="0";
        for (int i = 0; i < typeArticleList.size(); i++) {
            Article article= articleRepository.scannerArticle(String.valueOf(typeArticleList.get(i).getIdTypeArticle()),idCampus,idRoom);
            articles.add(article);
            idRoom= String.valueOf(article.getRoom().getIdRoom());
        }
        return articles;
    }
}
