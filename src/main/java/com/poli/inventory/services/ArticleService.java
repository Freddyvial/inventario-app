package com.poli.inventory.services;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.TypeArticle;

import java.util.List;

public interface ArticleService {

    List<Article> getArticle(String idCampus);
    Article createArticle(Article article) throws Exception;
    Article changeStateArticle(Article article);
    Article changeIdRoomArticle(Article article);
    List<Article> consulArticleByRoom(Integer idRoom);
    List<TypeArticle> getTypeArticle();
    List<Article> articlesForChanges(List<TypeArticle> typeArticleList, String idCampus);
}
