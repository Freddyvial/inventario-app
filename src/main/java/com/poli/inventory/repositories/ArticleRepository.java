package com.poli.inventory.repositories;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.TypeArticle;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface ArticleRepository {

    List<Article> getArticles(String idCampus);
    Article createArticle(Article article);
    Article checkArticle(String serial);
    Article update(Article article);
    Article changeStateArticle(Article article);
    Article changeIdRoomArticle(Article article);
    List<Article> consulArticleByRoom(Integer idRoom);
    List<TypeArticle> getTypeArticle();
}